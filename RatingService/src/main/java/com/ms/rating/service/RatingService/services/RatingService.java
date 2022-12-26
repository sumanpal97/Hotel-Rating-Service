package com.ms.rating.service.RatingService.services;

import com.ms.rating.service.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating saveRating(Rating rating);
    List<Rating> getAllRating();
    List<Rating> getRatingsByUserId(String userId);
    List<Rating> getRatingsByHotelId(String hotelId);

    //TODO : update
    //TODO : delete
}
