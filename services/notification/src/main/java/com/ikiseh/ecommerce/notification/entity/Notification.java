package com.ikiseh.ecommerce.notification.entity;

import com.ikiseh.ecommerce.kafka.order.OrderConfirmation;
import com.ikiseh.ecommerce.kafka.payment.PaymentConfirmation;
import com.ikiseh.ecommerce.notification.enumms.NotificationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Notification {

    @Id
    private String id;

    @Enumerated
    private NotificationType notificationType;

    private LocalDateTime notificationDate;

    private OrderConfirmation orderConfirmation;

    private PaymentConfirmation paymentConfirmation;

}
