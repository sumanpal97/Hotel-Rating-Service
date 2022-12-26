package com.ms.hotel.service.HotelService.services.impl;

import com.ms.hotel.service.HotelService.entities.Hotel;
import com.ms.hotel.service.HotelService.exceptions.ResourceNotFoundException;
import com.ms.hotel.service.HotelService.repositories.HotelRepository;
import com.ms.hotel.service.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        //generate unique userId
        String ranmdomHotelId = UUID.randomUUID().toString();
        hotel.setHotelId(ranmdomHotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(
                () -> new ResourceNotFoundException("Hotel with given Id is not found on server !! : " + hotelId));
    }
}
