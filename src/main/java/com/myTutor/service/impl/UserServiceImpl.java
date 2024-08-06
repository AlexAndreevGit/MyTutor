package com.myTutor.service.impl;


import com.myTutor.model.DTOs.UserRegisterDTO;
import com.myTutor.model.entity.*;
import com.myTutor.model.enums.CategoryNameEnum;
import com.myTutor.repo.CategoryRepository;
import com.myTutor.repo.TutoringRepository;
import com.myTutor.repo.UserRepository;
import com.myTutor.repo.UserRoleRepository;
import com.myTutor.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    //we are working with the interface so we achieve a higher level of abstraction
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;
    private final TutoringRepository tutoringRepository;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CategoryRepository categoryRepository, TutoringRepository tutoringRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;

        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.categoryRepository = categoryRepository;
        this.tutoringRepository = tutoringRepository;
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        User user = modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        UserRoleEntity userRoleEntityUser = userRoleRepository.getReferenceById(1L);
        List<UserRoleEntity> list = new ArrayList<>();
        list.add(userRoleEntityUser);
        user.setRoles(list);

        userRepository.save(user);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }


    @Override
    public User findById(Long currentUserId) {
        return userRepository.findById(currentUserId).orElse(null);
    }

    @Override
    public void initUsers() {


    }

    @Override
    public void deleteUserById(Long id) {

    }

}
