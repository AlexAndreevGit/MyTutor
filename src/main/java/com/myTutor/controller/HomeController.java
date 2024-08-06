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
    public String index(@AuthenticationPrincipal UserDetails userDetails,             //SpringSecurity_7 inject userDetails (details of the logged-in user)
                        Model model){

        if(userDetails == null){        //is the user is not logged in then we won't have userDetails
            return "index";
        }

        return "home";

    }

    @GetMapping("/info")
    public String informaticOffers(@AuthenticationPrincipal UserDetails userDetails,
                                   Model model){

        if(userDetails == null){
            return "index";
        }

        //Generate the needed lists
        List<TutorialViewDTO> informaticsTutorialsAsView = tutoringService.findAllByCategoryId(2);
        int countInformticsTutorials = informaticsTutorialsAsView.size();


        //Insert the lists in the home html
        model.addAttribute("informaticsTutorialsAsView",informaticsTutorialsAsView);
        model.addAttribute("countInformticsTutorials",countInformticsTutorials);


        return "homeInformatics";

    }

    @GetMapping("/math")
    public String mathematicOffers(@AuthenticationPrincipal UserDetails userDetails,             //XXXXXX
                                   Model model){

        if(userDetails == null){
            return "index";
        }


        List<TutorialViewDTO> mathematicsTutorialsAsView = tutoringService.findAllByCategoryId(1);
        int countMathematicsTutorials = mathematicsTutorialsAsView.size();


        model.addAttribute("mathematicsTutorialsAsView",mathematicsTutorialsAsView);
        model.addAttribute("countMathematicsTutorials",countMathematicsTutorials);


        return "homeMathematic";

    }

    @GetMapping("/data")
    public String dataScienceOffers(@AuthenticationPrincipal UserDetails userDetails,             //XXXXXX
                                    Model model){

        if(userDetails == null){
            return "index";
        }


        List<TutorialViewDTO> datascienceTutorialsAsView = tutoringService.findAllByCategoryId(3);
        int countDatascienceTutorials = datascienceTutorialsAsView.size();



        model.addAttribute("datascienceTutorialsAsView",datascienceTutorialsAsView);
        model.addAttribute("countDatascienceTutorials",countDatascienceTutorials);

        return "homeDataScience";

    }

    @GetMapping("/home")
    public String indexPage(){

        return "index";

    }

}
