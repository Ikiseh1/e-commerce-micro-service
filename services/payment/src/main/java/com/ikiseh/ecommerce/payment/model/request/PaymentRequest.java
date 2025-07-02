package com.ikiseh.ecommerce.payment.model.request;

import com.ikiseh.ecommerce.payment.enumms.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
