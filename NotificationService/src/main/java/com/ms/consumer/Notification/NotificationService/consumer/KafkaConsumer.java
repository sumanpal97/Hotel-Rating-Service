package com.ms.consumer.Notification.NotificationService.consumer;

import com.ms.consumer.Notification.NotificationService.payload.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.messaging.MessageHeaders;


@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
//    public void consume(Notification notification){
    public void receive(@Payload Notification notification, @Headers MessageHeaders headers) {
        log.info("Entering into KafkaConsumer : consumer");
        log.info(String.format("Json message recieved -> %s", notification.toString()));
        log.info("Headers : {}",headers);
        log.info("Exiting from KafkaConsumer : consumer");
    }
}
