package com.example.customerservice.customer;

import com.example.customerservice.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    public final CustomerRepository customerRepository;
    public final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public String updateCustomer(CustomerRequest request) {
        var  customer = customerRepository.findById(request.id()).orElseThrow(()->
                new CustomerNotFoundException( String.format("Customer can not be updated:: No Customer found with provided ID %s",request.id())));
        mergeCustomer(customer, request);
        customerRepository.save(customer);
        return customer.getId();
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.id())){
            customer.setId(request.id());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if((request.address() != null)){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper :: fromCustomer).collect(Collectors.toList());
    }

    public CustomerResponse findById(String customerId) {
       return customerRepository.findById(customerId).map(customerMapper::fromCustomer)
               .orElseThrow(() -> new CustomerNotFoundException(String.format("No Customer found with id %s",customerId)));
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    public Boolean exitsByID(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }
}
