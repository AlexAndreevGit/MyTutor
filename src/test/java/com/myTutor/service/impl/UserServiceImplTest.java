package com.myTutor.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

import com.myTutor.model.DTOs.UserRegisterDTO;
import com.myTutor.model.entity.Category;
import com.myTutor.model.entity.TutoringOffer;
import com.myTutor.model.entity.User;
import com.myTutor.model.entity.UserRoleEntity;
import com.myTutor.model.enums.CategoryNameEnum;
import com.myTutor.repo.CategoryRepository;
import com.myTutor.repo.TutoringRepository;
import com.myTutor.repo.UserRepository;
import com.myTutor.repo.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {


    //We mock the dependencies
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private TutoringRepository tutoringRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks //We use the @InjectMocks annotation to create an instance of UserService and inject the mocks into it.
    private UserServiceImpl userService;

    private UserRegisterDTO userRegisterDTO;
    private User user;

    @BeforeEach
    public void setUp() {

        // create instances of UserRegisterDTO and User
        userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setPassword("plainPassword");

        user = new User();
        user.setPassword("12345");
        user.setUsername("UN");

    }

    @Test
    public void testRegisterUser() {
        //define their behavior using when.
        when(modelMapper.map(any(UserRegisterDTO.class), any(Class.class))).thenReturn(user);
        userService.registerUser(userRegisterDTO);

        //verify: This method is used to verify that a specific method was called on a mock with specific arguments.
        verify(modelMapper).map(userRegisterDTO, User.class);
        verify(userRepository).save(user);
    }

    @Test
    public void testFindByUsernameAndPassword_UserFound() {
        //define their behavior using when.
        when(userRepository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.of(user));

        User result = userService.findByUsernameAndPassword("UN", "12345");

        assertEquals(user, result);
        verify(userRepository).findByUsernameAndPassword("UN", "12345");
    }

    @Test
    void findById() {
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        long id = 1 ;

        User result = userService.findById(id);

        assertEquals(user, result);

    }

//    @Test
//    void initUsers() {
//        when(userRepository.count()).thenReturn(0L);
//
//        // Define behavior for CategoryRepository.findByName
//        when(categoryRepository.findByName(CategoryNameEnum.MATHEMATICS)).thenReturn(new Category(CategoryNameEnum.MATHEMATICS));
//        when(categoryRepository.findByName(CategoryNameEnum.INFORMATICS)).thenReturn(new Category(CategoryNameEnum.INFORMATICS));
//        when(categoryRepository.findByName(CategoryNameEnum.DATASCIENCE)).thenReturn(new Category(CategoryNameEnum.DATASCIENCE));
//
//        // Define behavior for PasswordEncoder.encode
//        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
//
//        userService.initUsers();
//
//        // Verify that UserRepository.count was called
//        verify(userRepository, times(1)).count();
//
//        // Verify that UserRoleRepository.save was called twice
//        verify(userRoleRepository, times(2)).save(any(UserRoleEntity.class));
//
//        // Verify that UserRepository.save was called 4 times
//        verify(userRepository, times(4)).save(any(User.class));
//
//        // Verify that CategoryRepository.findByName was called for each category
//        verify(categoryRepository, times(1)).findByName(CategoryNameEnum.MATHEMATICS);
//        verify(categoryRepository, times(1)).findByName(CategoryNameEnum.INFORMATICS);
//        verify(categoryRepository, times(1)).findByName(CategoryNameEnum.DATASCIENCE);
//
//        // Verify that TutoringRepository.save was called for each offer
////        verify(tutoringRepository, times(1)).save(any(TutoringOffer.class));
//
//    }
}
