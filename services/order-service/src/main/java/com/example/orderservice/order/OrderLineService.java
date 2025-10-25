package com.example.orderservice.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;
    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
       return orderLineRepository.save(orderLineMapper.toOrderLineMapper(orderLineRequest)).getId();
    }

    public List<OrderLineResponse> getOrderLineById(Integer orderId){
        return orderLineRepository.findAllByOrderId(orderId).stream().map((orderLineMapper ::fromOrderLineMapper)).collect(Collectors.toList());

    }
}
