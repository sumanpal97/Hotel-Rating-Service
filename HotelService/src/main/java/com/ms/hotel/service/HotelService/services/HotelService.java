package com.ms.hotel.service.HotelService.services;

import com.ms.hotel.service.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    Hotel saveHotel(Hotel hotel);
    List<Hotel> getAllHotel();
    Hotel getHotel(String hotelId);

    //TODO : update
    //TODO : delete
}
