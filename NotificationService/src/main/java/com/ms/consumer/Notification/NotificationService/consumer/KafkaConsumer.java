package com.ms.consumer.Notification.NotificationService.consumer;

import com.ms.consumer.Notification.NotificationService.payload.Notification;
import com.ms.consumer.Notification.NotificationService.services.impl.NotificationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.messaging.MessageHeaders;

import java.util.UUID;


@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    NotificationServiceImpl notificationService;

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
//    public void consume(Notification notification){
    public void receive(@Payload Notification notification, @Headers MessageHeaders headers) {
        log.info("Entering into KafkaConsumer : consumer");
        log.info(String.format("Json message recieved -> %s", notification.toString()));
        log.info("Headers : {}",headers);

        String ranmdomNotificationId = UUID.randomUUID().toString();
        notification.setNotificationID(ranmdomNotificationId);
        notificationService.saveNotification(notification);

        log.info("Exiting from KafkaConsumer : consumer");
    }
}
