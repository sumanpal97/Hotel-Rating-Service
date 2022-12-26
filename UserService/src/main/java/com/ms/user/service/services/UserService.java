package com.ms.user.service.services;

import com.ms.user.service.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUser();
    User getUser(String userId);

    //TODO : update
    //TODO : delete
}
