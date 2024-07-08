package com.myTutor.service.impl;


import com.myTutor.model.entity.User;
import com.myTutor.model.entity.UserRoleEntity;
import com.myTutor.model.entity.UserRoleEnum;
import com.myTutor.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)// So I can use the @Mock...
class MyTutorUserDetailsServiceTest {

    private MyTutorUserDetailsService serviceToTest;

    //This is Object is coming from mochito. It is an empty object
    //"MyTutorUserDetailsService" is requiring a repository so we provide one
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach//befor each test
    void setUp() {
        serviceToTest = new MyTutorUserDetailsService(mockUserRepository);
    }

    @Test
    void testMock() {

        User user = new User("UN", "P", "E");

        when(mockUserRepository.findByUsername("user1")).thenReturn(Optional.of(user));

        Optional<User> userOptional = mockUserRepository.findByUsername("user1");

        User user1 = userOptional.get();

        Assertions.assertEquals("UN", user1.getUsername());
//        Assertions.assertEquals(user,user1);
    }

    @Test
    void testUserNotFound() {

        //we make the test fail by returning a user and not throwing th eexception
//        when(mockUserRepository.findByUsername("user1")).
//                thenReturn(Optional.of(new User()));

        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("user1"));
    }


    @Test
    void testUserFoundException(){
        //Arange
        User testUserEntity = createTestUser();
        when(mockUserRepository.findByUsername(testUserEntity.getUsername())).thenReturn(Optional.of(testUserEntity));

        //Act
        UserDetails userDetails = serviceToTest.loadUserByUsername(testUserEntity.getUsername());

        //Assert
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(testUserEntity.getUsername(),userDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(),userDetails.getPassword());
    }

    private static User createTestUser(){
        return new User("Username","Password","email");
    }

}
