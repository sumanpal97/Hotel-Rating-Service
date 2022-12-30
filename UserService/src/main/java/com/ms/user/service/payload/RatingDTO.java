package com.ms.user.service.payload;

import com.ms.user.service.entities.Hotel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
    private String ratingId;
    private String userId;
    private String hotelId;
    private Integer rating;
    private String feedback;
    private HoteldDTO hotel;
}