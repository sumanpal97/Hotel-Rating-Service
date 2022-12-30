package com.ms.consumer.Notification.NotificationService.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HoteldDTO {
    private String hotelId;

    private String name;

    private String location;

    private String about;
}
