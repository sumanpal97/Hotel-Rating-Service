package com.ms.hotel.service.HotelService.services.impl;

import com.ms.hotel.service.HotelService.entities.Hotel;
import com.ms.hotel.service.HotelService.exceptions.ResourceNotFoundException;
import com.ms.hotel.service.HotelService.repositories.HotelRepository;
import com.ms.hotel.service.HotelService.services.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        log.info("Entering into HotelServiceImpl : saveHotel");
        //generate unique userId
        String ranmdomHotelId = UUID.randomUUID().toString();
        hotel.setHotelId(ranmdomHotelId);
        log.info("Exiting from HotelServiceImpl : saveHotel");
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        log.info("Entering into HotelServiceImpl : getAllHotel");
        List<Hotel> all = hotelRepository.findAll();
        log.info("Exiting from HotelServiceImpl : getAllHotel");
        return all;
    }

    @Override
    public Hotel getHotel(String hotelId) {
        log.info("Entering into HotelServiceImpl : getHotel");
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(
                () -> new ResourceNotFoundException("Hotel with given Id is not found on server !! : " + hotelId));
        log.info("Exiting from HotelServiceImpl : getHotel");
        return hotel;
    }
}
