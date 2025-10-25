package com.example.orderservice.order;

import com.example.orderservice.customer.CustomerClient;
import com.example.orderservice.kafka.OrderConfirmation;
import com.example.orderservice.kafka.OrderProducer;
import com.example.orderservice.order.exception.BusinessException;
import com.example.orderservice.payment.PaymentClient;
import com.example.orderservice.payment.PaymentRequest;
import com.example.orderservice.product.ProductClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository  orderRepository;
    private final OrderMapper  orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest orderRequest) {
        //Check Customer Id is present
        var customer= this.customerClient.findCustomersById(orderRequest.customer_id()).orElseThrow(()-> new BusinessException("Can not create order:: Customer does not exist"));
        // Place product purchase
        var purchasedProducts =this.productClient.purchaseProducts(orderRequest.products());
        // Persist Order
        var order = orderRepository.save(orderMapper.toOrder(orderRequest));

        for(PurchaseRequest purchaseRequest : orderRequest.products()) {
        orderLineService.saveOrderLine(new OrderLineRequest(null,purchaseRequest.productId(),purchaseRequest.quantity(),order.getId()));
        }

        //  Start Payment process
        var paymentRequest = new PaymentRequest(orderRequest.totalAmount(),orderRequest.payment_method(),order.getId(), order.getReference(),customer);
        this.paymentClient.requestOrderPayment(paymentRequest);


        // Send Order Confirmation
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                orderRequest.reference(),orderRequest.totalAmount(),customer,orderRequest.payment_method(),purchasedProducts)
        );

        return order.getId();

    }

    public List<OrderResponse> getAllOrders(){
        return orderRepository.findAll().stream().map(orderMapper :: fromOrder).collect(Collectors.toList());
    }


    public OrderResponse getOrder(Integer orderId){
        return orderRepository.findById(orderId).map(orderMapper ::fromOrder).orElseThrow(() -> new EntityNotFoundException(String.format("Can not find order:: Order id not found %d",orderId)));
    }
}
