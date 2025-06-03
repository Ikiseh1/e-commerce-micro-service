package com.ikiseh.ecommerce.customer.repository;

import com.ikiseh.ecommerce.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
