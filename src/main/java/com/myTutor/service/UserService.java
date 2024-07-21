package com.myTutor.service;

import com.myTutor.model.DTOs.UserRegisterDTO;
import com.myTutor.model.entity.User;

public interface UserService {

    void registerUser(UserRegisterDTO userRegisterDTO);

    User findByUsernameAndPassword(String username, String password);

    User findById(Long currentUserId);

    void initUsers();
}
