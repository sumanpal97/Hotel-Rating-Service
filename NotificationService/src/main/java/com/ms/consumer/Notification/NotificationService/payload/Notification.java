package com.ms.consumer.Notification.NotificationService.payload;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document("notification")
public class Notification {
    @Id
    private String notificationID;
    String message;
    String status;
    UserDTO user;
}
