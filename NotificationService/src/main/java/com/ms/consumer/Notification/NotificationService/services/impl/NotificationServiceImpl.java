package com.ms.consumer.Notification.NotificationService.services.impl;

import com.ms.consumer.Notification.NotificationService.payload.Notification;
import com.ms.consumer.Notification.NotificationService.repositories.NotificationRepository;
import com.ms.consumer.Notification.NotificationService.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification saveNotification(Notification notification) {

        return notificationRepository.save(notification);
    }
}
