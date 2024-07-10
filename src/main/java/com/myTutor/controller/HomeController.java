package com.myTutor.controller;

import com.myTutor.model.DTOs.TutorialViewDTO;
import com.myTutor.service.TutoringService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final TutoringService tutoringService;

    public HomeController( TutoringService tutoringService) {
        this.tutoringService = tutoringService;
    }

    @GetMapping()
    public String index(@AuthenticationPrincipal UserDetails userDetails,             //SpringSecurity_7 inject userDetails
                        Model model){

        if(userDetails == null){        //is the user is not logged in then we won't have userDetails
            return "index";
        }

        //String name = userDetails.getUsername();

        //Generate the needed lists
        List<TutorialViewDTO> informaticsTutorialsAsView = tutoringService.findAllByCategoryId(2);
        int countInformticsTutorials = informaticsTutorialsAsView.size();

        List<TutorialViewDTO> mathematicsTutorialsAsView = tutoringService.findAllByCategoryId(1);
        int countMathematicsTutorials = mathematicsTutorialsAsView.size();

        List<TutorialViewDTO> datascienceTutorialsAsView = tutoringService.findAllByCategoryId(3);
        int countDatascienceTutorials = datascienceTutorialsAsView.size();


        //Insert the lists in the home html
        model.addAttribute("informaticsTutorialsAsView",informaticsTutorialsAsView);
        model.addAttribute("countInformticsTutorials",countInformticsTutorials);

        model.addAttribute("mathematicsTutorialsAsView",mathematicsTutorialsAsView);
        model.addAttribute("countMathematicsTutorials",countMathematicsTutorials);

        model.addAttribute("datascienceTutorialsAsView",datascienceTutorialsAsView);
        model.addAttribute("countDatascienceTutorials",countDatascienceTutorials);

        return "home";

    }

    @GetMapping("/home")
    public String indexPage(){

        return "index";

    }

}
