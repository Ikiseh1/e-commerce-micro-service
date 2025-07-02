package com.ikiseh.ecommerce.kafka;

import com.ikiseh.ecommerce.customer.CustomerResponse;
import com.ikiseh.ecommerce.order.enumms.PaymentMethod;
import com.ikiseh.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,

        BigDecimal totalAmount,

        PaymentMethod paymentMethod,

        CustomerResponse customer,

        List<PurchaseResponse> product

) {
}
