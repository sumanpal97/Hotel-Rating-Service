package com.ms.consumer.Notification.NotificationService.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notification {
    String message;
    String status;
    UserDTO user;
}
