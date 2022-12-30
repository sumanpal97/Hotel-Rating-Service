package com.ms.consumer.Notification.NotificationService.payload;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private String userId;

    private String name;

    private String email;

    private String about;

    private List<RatingDTO> ratings = new ArrayList<>();
}
