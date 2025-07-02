package com.ikiseh.ecommerce.payment.service;

import com.ikiseh.ecommerce.notification.NotificationProducer;
import com.ikiseh.ecommerce.notification.PaymentNotificationRequest;
import com.ikiseh.ecommerce.payment.model.request.PaymentRequest;
import com.ikiseh.ecommerce.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest paymentRequest) {

        var payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.orderReference(),
                        paymentRequest.amount(),
                        paymentRequest.paymentMethod(),
                        paymentRequest.customer().firstname(),
                        paymentRequest.customer().lastname(),
                        paymentRequest.customer().email()
                )
        );

        return payment.getId();
    }
}
