package com.ikiseh.ecommerce.customer.service;


import com.ikiseh.ecommerce.customer.entity.Customer;
import com.ikiseh.ecommerce.customer.model.request.CustomerRequest;
import com.ikiseh.ecommerce.customer.model.response.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest customerRequest){
        if (customerRequest == null){
            return null;
        }
        return Customer.builder()
                .id(customerRequest.id())
                .firstname(customerRequest.firstname())
                .lastname(customerRequest.lastname())
                .email(customerRequest.email())
                .address(customerRequest.address())
                .build();
    }


    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }


}
