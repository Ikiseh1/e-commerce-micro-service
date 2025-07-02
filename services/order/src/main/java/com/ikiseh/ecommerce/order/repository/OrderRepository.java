package com.ikiseh.ecommerce.order.repository;

import com.ikiseh.ecommerce.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
