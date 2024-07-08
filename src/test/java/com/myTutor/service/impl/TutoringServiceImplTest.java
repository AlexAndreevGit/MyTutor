package com.myTutor.service.impl;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.myTutor.model.DTOs.TutorialViewDTO;
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

import java.util.ArrayList;
import java.util.List;
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
    private TutorialViewDTO tutorialViewDTO;

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

        tutorialViewDTO = new TutorialViewDTO();
        tutorialViewDTO.setEmailOfTheTutor(user.getEmail());
        tutoringOffer.setAddedBy(user);
    }

    @Test
    public void testAddTutoringOffer() {

        when(modelMapper.map(tutorialAddDTO, TutoringOffer.class)).thenReturn(tutoringOffer);

        tutoringService.addTutoringOffer(tutorialAddDTO, "testUser");

        verify(tutoringRepository, times(1)).save(any(TutoringOffer.class));
    }

    @Test
    void findAllByCategoryId() {

        List<TutoringOffer> offers = new ArrayList<>();
        offers.add(tutoringOffer);

        when(tutoringRepository.findAllByCategoryId(1)).thenReturn(offers);
        when(modelMapper.map(tutoringOffer, TutorialViewDTO.class)).thenReturn(tutorialViewDTO);

        List<TutorialViewDTO> result = tutoringService.findAllByCategoryId(1);

        assertEquals(1,result.size());
        assertEquals("testUser@example.com",result.get(0).getEmailOfTheTutor());

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