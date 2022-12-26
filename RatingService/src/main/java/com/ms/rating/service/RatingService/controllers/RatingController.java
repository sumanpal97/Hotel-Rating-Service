package com.ms.rating.service.RatingService.controllers;

import com.ms.rating.service.RatingService.entities.Rating;
import com.ms.rating.service.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating savedRating = ratingService.saveRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRating);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating(){
        List<Rating> ratingList = ratingService.getAllRating();
        return ResponseEntity.ok(ratingList);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        List<Rating> ratingListByUserId = ratingService.getRatingsByUserId(userId);
        return ResponseEntity.ok(ratingListByUserId);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        List<Rating> ratingListByHotelId = ratingService.getRatingsByHotelId(hotelId);
        return ResponseEntity.ok(ratingListByHotelId);
    }
}
