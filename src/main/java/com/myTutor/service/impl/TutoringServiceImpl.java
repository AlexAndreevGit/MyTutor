package com.myTutor.service.impl;

import com.myTutor.model.DTOs.TutorialAddDTO;
import com.myTutor.model.DTOs.TutorialViewDTO;
import com.myTutor.model.entity.Category;
import com.myTutor.model.entity.TutoringOffer;
import com.myTutor.model.entity.User;
import com.myTutor.repo.CategoryRepository;
import com.myTutor.repo.TutoringRepository;
import com.myTutor.repo.UserRepository;
import com.myTutor.service.TutoringService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TutoringServiceImpl implements TutoringService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final TutoringRepository tutoringRepository;
    private final CategoryRepository categoryRepository;

    public TutoringServiceImpl(ModelMapper modelMapper, UserRepository userRepository, TutoringRepository tutoringRepository, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.tutoringRepository = tutoringRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addTutoringOffer(TutorialAddDTO tutorialAddDTO, String userName) {

        TutoringOffer tutoringOffer = modelMapper.map(tutorialAddDTO, TutoringOffer.class);

        User user = userRepository.findByUsername(userName).orElse(null);

        tutoringOffer.setAddedBy(user);

        Category category = categoryRepository.findByName(tutorialAddDTO.getCategory());

        tutoringOffer.setCategory(category);

        tutoringRepository.save(tutoringOffer);

    }

    @Override
    public List<TutorialViewDTO> findAllByCategoryId(int i) {

        List<TutoringOffer> listOfAllOffersAsObject = tutoringRepository.findAllByCategoryId(i);

        List<TutorialViewDTO> liatOfAllOffersAsViewDTO = new ArrayList<>();

        for (TutoringOffer tutoringOffer : listOfAllOffersAsObject) {
            TutorialViewDTO tutorialViewDTO = modelMapper.map(tutoringOffer, TutorialViewDTO.class);

            tutorialViewDTO.setEmailOfTheTutor(tutoringOffer.getAddedBy().getEmail());
            liatOfAllOffersAsViewDTO.add(tutorialViewDTO);
        }
        System.out.println("test");

        return liatOfAllOffersAsViewDTO;

    }

//    @Override
//    public void addToFavouries(Long id, long recipeId) {
//
//        Optional<User> userOptional = userRepository.findById(id);
//
//        if (userOptional.isEmpty()) {
//            return;
//        }
//
//        Optional<TutoringOffer> recipeOptional = tutoringRepository.findById(recipeId);
//
//        if (recipeOptional.isEmpty()) {
//            return;
//        }
//
//        userOptional.get().addFavorite(recipeOptional.get());
//
//        userRepository.save(userOptional.get());  // ako ne e optional bez get?????
//
//    }

    @Override
    public List<TutorialViewDTO> findByAddedById(Long id) {

        List<TutoringOffer> tutoringOffersAsObject = tutoringRepository.findByAddedById(id);
        List<TutorialViewDTO> liatOfAllOffersAsViewDTO = new ArrayList<>();

        for (TutoringOffer tutoringOffer : tutoringOffersAsObject) {
            TutorialViewDTO tutorialViewDTO = modelMapper.map(tutoringOffer, TutorialViewDTO.class);



            liatOfAllOffersAsViewDTO.add(tutorialViewDTO);
        }


        return liatOfAllOffersAsViewDTO;
    }

    @Override
    public void removeOffer(Long id) {
        tutoringRepository.deleteById(id);
    }

    @Override
    public void initTutoringOffers() {

//        if (tutoringRepository.count() == 0) {
//            TutoringOffer tutoringOffer = new TutoringOffer();
//        }

    }


}
