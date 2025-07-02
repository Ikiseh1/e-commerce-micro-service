package com.ikiseh.ecommerce.order.service;

import com.ikiseh.ecommerce.customer.CustomerClient;
import com.ikiseh.ecommerce.exception.BusinessException;
import com.ikiseh.ecommerce.kafka.OrderConfirmation;
import com.ikiseh.ecommerce.kafka.OrderProducer;
import com.ikiseh.ecommerce.order.model.request.OrderRequest;
import com.ikiseh.ecommerce.order.model.response.OrderResponse;
import com.ikiseh.ecommerce.order.repository.OrderRepository;
import com.ikiseh.ecommerce.orderLine.OrderLineRequest;
import com.ikiseh.ecommerce.orderLine.OrderLineService;
import com.ikiseh.ecommerce.payment.PaymentClient;
import com.ikiseh.ecommerce.payment.PaymentRequest;
import com.ikiseh.ecommerce.product.ProductClient;
import com.ikiseh.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest orderRequest) {
        //check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        //purchase the product --> product-ms (RestTemplate)
        var purchasedProducts = this.productClient.purchaseProducts(orderRequest.products());

        //persist oder lines
        var order = this.orderRepository.save(orderMapper.toOrder(orderRequest));

        for(PurchaseRequest purchaseRequest: orderRequest.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );

        }

        //start payment process
        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                orderRequest.id(),
                orderRequest.reference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);


        //send the order confirmation --> notification-ms (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts

                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", orderId)));
    }
}
