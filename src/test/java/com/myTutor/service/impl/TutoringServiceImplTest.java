package com.myTutor.service.impl;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.myTutor.model.enums.CategoryNameEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.myTutor.model.DTOs.TutorialAddDTO;
import com.myTutor.model.entity.Category;
import com.myTutor.model.entity.TutoringOffer;
import com.myTutor.model.entity.User;
import com.myTutor.repo.CategoryRepository;
import com.myTutor.repo.TutoringRepository;
import com.myTutor.repo.UserRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TutoringServiceImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TutoringRepository tutoringRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private TutoringServiceImpl tutoringService;

    private TutorialAddDTO tutorialAddDTO;
    private User user;
    private Category category;
    private TutoringOffer tutoringOffer;

    @BeforeEach
    public void setUp() {
        tutorialAddDTO = new TutorialAddDTO();
        tutorialAddDTO.setDescription("Description of Math Tutoring");
        tutorialAddDTO.setCategory(CategoryNameEnum.MATHEMATICS);
        tutorialAddDTO.setPrice(30.0);

        user = new User();
        user.setUsername("testUser");
        user.setEmail("testUser@example.com");

        category = new Category();
        category.setName(CategoryNameEnum.MATHEMATICS);

        tutoringOffer = new TutoringOffer();
        tutoringOffer.setDescription(tutorialAddDTO.getDescription());
        tutoringOffer.setPrice(tutorialAddDTO.getPrice());
    }

    @Test
    public void testAddTutoringOffer() {

        //public void addTutoringOffer(TutorialAddDTO tutorialAddDTO, String userName) {

        when(modelMapper.map(tutorialAddDTO, TutoringOffer.class)).thenReturn(tutoringOffer);
        when(categoryRepository.findByName(any(CategoryNameEnum.class))).thenReturn(category);

        tutoringService.addTutoringOffer(tutorialAddDTO, "testUser");

        verify(tutoringRepository, times(1)).save(any(TutoringOffer.class));
    }

    @Test
    void findAllByCategoryId() {
    }

    @Test
    void addToFavouries() {
    }

    @Test
    void findByAddedById() {
    }

    @Test
    void removeOffer() {
    }

    @Test
    void initTutoringOffers() {
    }
}