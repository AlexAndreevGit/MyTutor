package com.myTutor.service;

import com.myTutor.model.DTOs.UserRegisterDTO;
import com.myTutor.model.entity.User;

public interface UserService {

    //void registerUser(User user);
    void registerUser(UserRegisterDTO userRegisterDTO);

    User findByUsernameAndPassword(String username, String password);

//    void logInUser(Long id, String username); // this method will not be necessery later

    User findById(Long currentUserId);

    void initUsers();
}
