package com.ms.consumer.Notification.NotificationService.repositories;

import com.ms.consumer.Notification.NotificationService.payload.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,String> {

}
