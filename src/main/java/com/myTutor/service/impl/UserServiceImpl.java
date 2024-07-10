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

//        UserRoleEntity userRoleEntity = new UserRoleEntity();   //TODO Save not possbile if user role is added
//        userRoleEntity.setRole(UserRoleEnum.USER);
//        user.getRoles().add(userRoleEntity);

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

        if (userRepository.count() == 0) {

            // ----- User roles in the Database -----
            UserRoleEntity userRoleEntityUser = new UserRoleEntity(UserRoleEnum.USER);
            UserRoleEntity userRoleEntityAdmin = new UserRoleEntity(UserRoleEnum.ADMIN);

            userRoleRepository.save(userRoleEntityUser);
            userRoleRepository.save(userRoleEntityAdmin);


            // ----- Users in the Database -----
            User userAdmin = new User("admin1", passwordEncoder.encode("12345"), "admin1@gmail.com");

            List<UserRoleEntity> list = new ArrayList<>();
            list.add(userRoleEntityAdmin);
            userAdmin.setRoles(list);

            userRepository.save(userAdmin);

            User user1 = new User("user1", passwordEncoder.encode("12345"), "u1@gmail.com");
            userRepository.save(user1);

            User user2 = new User("user2", passwordEncoder.encode("12345"), "u2@gmail.com");
            userRepository.save(user2);

            User user3 = new User("user3", passwordEncoder.encode("12345"), "u3@gmail.com");
            userRepository.save(user3);


            // ----- Offers in the Database -----
            Category category1Math = categoryRepository.findByName(CategoryNameEnum.MATHEMATICS);
            Category category2Info = categoryRepository.findByName(CategoryNameEnum.INFORMATICS);
            Category category3Data = categoryRepository.findByName(CategoryNameEnum.DATASCIENCE);

            //Mathematics offer
            TutoringOffer tutoringOfferMath1 = new TutoringOffer("Advanced mathematics 1", "Description math offer 1", 22.3, category1Math, user1);
            tutoringRepository.save(tutoringOfferMath1);

            TutoringOffer tutoringOfferMath2 = new TutoringOffer("Linear algebra", "Description math offer 2", 12.3, category1Math, user1);
            tutoringRepository.save(tutoringOfferMath2);

            TutoringOffer tutoringOfferMath3 = new TutoringOffer("Math. Integral solving", "Description math offer 3", 32.3, category1Math, user1);
            tutoringRepository.save(tutoringOfferMath3);

            //Informaticss offer
            TutoringOffer tutoringOfferInfo1 = new TutoringOffer("Info1", "Description info offer 1", 32.3, category2Info, user2);
            tutoringRepository.save(tutoringOfferInfo1);

            TutoringOffer tutoringOfferInfo2 = new TutoringOffer("Info2", "Description info offer 2", 15.3, category2Info, user2);
            tutoringRepository.save(tutoringOfferInfo2);

            TutoringOffer tutoringOfferInfo3 = new TutoringOffer("Info3", "Description info offer 3", 37.3, category2Info, user2);
            tutoringRepository.save(tutoringOfferInfo3);

            //Datascience offer
            TutoringOffer tutoringOfferDatascience1 = new TutoringOffer("Data1", "Description Datascience offer 1", 32.3, category3Data, user3);
            tutoringRepository.save(tutoringOfferDatascience1);


            // ----- User roles in the Database -----
//            UserRoleEntity userRoleEntityUser = new UserRoleEntity(UserRoleEnum.USER);
//            UserRoleEntity userRoleEntityAdmin = new UserRoleEntity(UserRoleEnum.ADMIN);
//
//            userRoleRepository.save(userRoleEntityUser);
//            userRoleRepository.save(userRoleEntityAdmin);

        }

    }

}
