package com.myTutor.service;

import com.myTutor.model.DTOs.TutorialAddDTO;
import com.myTutor.model.DTOs.TutorialViewDTO;

import java.util.List;

public interface TutoringService {

    void addTutoringOffer(TutorialAddDTO tutorialAddDTO, String userName);

    List<TutorialViewDTO> findAllByCategoryId(int i);


    List<TutorialViewDTO> findByAddedById(Long id);

    void removeOffer(Long id);

}
