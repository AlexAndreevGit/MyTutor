package com.myTutor.controller;

import com.myTutor.model.DTOs.TutorialViewDTO;
import com.myTutor.model.entity.User;
import com.myTutor.repo.UserRepository;
import com.myTutor.service.TutoringService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class StaticticsContoller {


    private final TutoringService tutoringService;
    private final UserRepository userRepository;

    public StaticticsContoller(TutoringService tutoringService, UserRepository userRepository) {
        this.tutoringService = tutoringService;
        this.userRepository = userRepository;
    }


    @GetMapping("/statistics")
    public String login(Model model){

        List<TutorialViewDTO> informaticsTutorialsAsView = tutoringService.findAllByCategoryId(2);
        int countInformticsTutorials = informaticsTutorialsAsView.size();

        List<TutorialViewDTO> mathematicsTutorialsAsView = tutoringService.findAllByCategoryId(1);
        int countMathematicsTutorials = mathematicsTutorialsAsView.size();

        List<TutorialViewDTO> datascienceTutorialsAsView = tutoringService.findAllByCategoryId(3);
        int countDatascienceTutorials = datascienceTutorialsAsView.size();

        List<User> allUsers = userRepository.findAll();
        int countAllUsers = allUsers.size();
        countAllUsers--; // we don't count the admin as a user


        model.addAttribute("countInformticsTutorials",countInformticsTutorials);

        model.addAttribute("countMathematicsTutorials",countMathematicsTutorials);

        model.addAttribute("countDatascienceTutorials",countDatascienceTutorials);

        model.addAttribute("countAllUsers",countAllUsers);

        return "statistics";

    }

}
