package com.ikiseh.ecommerce.notification.repository;

import com.ikiseh.ecommerce.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, String> {
}
