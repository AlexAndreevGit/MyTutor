package com.myTutor.controller;

import com.myTutor.model.DTOs.TutorialAddDTO;
import com.myTutor.service.TutoringService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tutoriels" )
public class TutorialsController {

private final TutoringService tutoringService;

    public TutorialsController(TutoringService tutoringService) {
        this.tutoringService = tutoringService;
    }

    @GetMapping("/add")
    public String login(){
        return "tutorial-add";
    }

    @ModelAttribute("tutorialAddDTO")
    public TutorialAddDTO initTutorialAddDTO(){
        return new TutorialAddDTO();
    }

    @PostMapping("/add")
    private String addConfirm(@Valid TutorialAddDTO tutorialAddDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,@AuthenticationPrincipal UserDetails userDetails){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("tutorialAddDTO", tutorialAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.tutorialAddDTO", bindingResult);

            return "redirect:add";

        }

        String userName = userDetails.getUsername();

        tutoringService.addTutoringOffer(tutorialAddDTO,userName);

        return "redirect:/";

    }


    @GetMapping("/remove/{id}")
    public String ready(@PathVariable Long id){
        tutoringService.removeOffer(id);

        return "redirect:/";
    }

}
