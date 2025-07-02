package com.ikiseh.ecommerce.order.model.response;

import com.ikiseh.ecommerce.order.enumms.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(

        Integer id,

        String reference,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        String customerId
) {
}
