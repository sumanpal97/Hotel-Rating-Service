package com.ms.user.service.services.impl;

import com.ms.user.service.entities.Hotel;
import com.ms.user.service.entities.Rating;
import com.ms.user.service.entities.User;
import com.ms.user.service.exceptions.ResourceNotFoundException;
import com.ms.user.service.externalGateway.HotelServiceClient;
import com.ms.user.service.payload.Notification;
import com.ms.user.service.payload.UserDTO;
import com.ms.user.service.producer.KafkaProducer;
import com.ms.user.service.repositories.UserRepository;
import com.ms.user.service.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelServiceClient hotelServiceClient;

    @Autowired
    private KafkaProducer kafkaProducer;
    @Override
    public User saveUser(User user) {
        log.info("Entering into UserServiceImpl : saveUser");

        //generate unique userId
        String ranmdomUserId = UUID.randomUUID().toString();
        user.setUserId(ranmdomUserId);
        User savedUser = userRepository.save(user);

        log.info("Async Call for Producing Notification : {}",user);
        Notification notification = convert(savedUser);
        kafkaProducer.sendMessage(notification);

        log.info("Exiting from UserServiceImpl : saveUser");
        return savedUser;
    }

    private Notification convert(User user) {
        Notification notification = new Notification();
        notification.setMessage("User Has Been Created");
        notification.setStatus("OK");

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setAbout(user.getAbout());
        userDTO.setEmail(user.getEmail());

        notification.setUser(userDTO);

        return notification;
    }

    @Override
    public List<User> getAllUser() {
        //TODO : map all rating with their respective user
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public User getUser(String userId) {
        //get user from DB with the help of user repository
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with given Id is not found on server !! : " + userId));
        /*without connecting to RATING-Service ,we are returning empty arraylist of rating
        for returning original Rating list
        we need to fetch rating of the above user, from RATING Service
        API of RATING Service : /users/{userId}
        URL of RATING Service : http://localhost:8083/ratings/users/5365cef2-d7c9-4ec3-806d-d1f3bb609dcc
        We can call by using client
        Client can be Rest Template or by Feign Client*/
//        String ratingServiceUrl = "http://localhost:8083/ratings/users/"+userId;
        String ratingServiceUrl = "http://RATING-SERVICE/ratings/users/"+userId;

        Rating[] userRatingArr = restTemplate.getForObject(ratingServiceUrl, Rating[].class);
        List<Rating> userRatingList = Arrays.stream(userRatingArr).collect(Collectors.toList());
        log.info("User Rating List : {}",userRatingList);

        List<Rating> ratingList = userRatingList.stream().map(
                rating -> {
                    String hotelId = rating.getHotelId();
                    //USING REST TEMPLATE
//                    String hotelServiceUrl = "http://localhost:8082/hotels/" + hotelId;
//                    String hotelServiceUrl = "http://HOTEL-SERVICE/hotels/" + hotelId;
//                    Hotel hotel = restTemplate.getForObject(hotelServiceUrl , Hotel.class);
                    //USING FEIGN CLIENT
                    Hotel hotel = hotelServiceClient.getHotel(hotelId);
                    log.info("Hotel : {}", hotel);
                    rating.setHotel(hotel);
                    return rating;
                }
        ).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }

    /*
    *
    * CONNECTION B/W TWO MS
    *
    *
    A. Rest Template
    1. Make Bean of RestTemplate in Main Class OR In other class but use @Configuration at class level & @LoadBalanced at method level
    2. @Autowired of RestTemplate in the Service Class
    3. restTemplate.getForObject ....

    B. Feign Client
    1. Use Dependency
    2. @EnableFeignClients in Main Class
    3. Create a ExternalGateway package and an Client Interface to call other MS method (methodX())
    4. In Client Interface use FeignClient(name="")
    5. Make method in interface with the same path to redirect call in other MS
    6. @Autowired of the Client Interface
    7. Object o = client.methodX();
    *
    *
    * */
}
