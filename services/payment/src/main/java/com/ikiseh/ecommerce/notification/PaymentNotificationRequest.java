package com.ikiseh.ecommerce.notification;

import com.ikiseh.ecommerce.payment.enumms.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        String customerFirstName,

        String customerLastName,

        String customerEmail

) {
}
