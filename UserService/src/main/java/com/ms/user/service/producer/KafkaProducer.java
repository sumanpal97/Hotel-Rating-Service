package com.ms.user.service.producer;

import com.ms.user.service.entities.User;
import com.ms.user.service.payload.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {
    @Value("${spring.kafka.topic-json.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Notification> kafkaTemplate;

    public void sendMessage(Notification data){
        log.info("Entering into KafkaProducer : sendMessage");
        log.info(String.format("Message sent -> %s", data.toString()));

        Message<Notification> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        kafkaTemplate.send(message);
        log.info("Exiting from KafkaProducer : sendMessage");
    }
}
