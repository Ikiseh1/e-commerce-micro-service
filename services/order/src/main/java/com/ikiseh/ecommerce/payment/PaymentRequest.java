package com.ikiseh.ecommerce.payment;

import com.ikiseh.ecommerce.customer.CustomerResponse;
import com.ikiseh.ecommerce.order.enumms.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
