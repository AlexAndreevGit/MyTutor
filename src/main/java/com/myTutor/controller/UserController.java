package com.myTutor.controller;

import com.myTutor.model.DTOs.TutorialViewDTO;
import com.myTutor.model.DTOs.UserLogInDTO;
import com.myTutor.model.DTOs.UserRegisterDTO;
import com.myTutor.model.entity.User;
import com.myTutor.repo.UserRepository;
import com.myTutor.service.TutoringService;
import com.myTutor.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final TutoringService tutoringService;
    private final UserRepository userRepository;

    public UserController(UserService userService, ModelMapper modelMapper, TutoringService tutoringService, UserRepository userRepository) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.tutoringService = tutoringService;
        this.userRepository = userRepository;
    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO initUserRegisterDTO(){
        return new UserRegisterDTO();
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterDTO userRegisterDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors() || !userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())){

            System.out.println("Test");
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO",bindingResult);

            return "redirect:register";
        }

        userService.registerUser(userRegisterDTO);
        return "redirect:login";

    }

    @ModelAttribute("userLogInDTO")
    public UserLogInDTO initUserLogInDTO(){
        return new UserLogInDTO();
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/about-us")
    public String aboutUs(){
        return "about-us";
    }

    @GetMapping("/my-information")                  //TODO Current user
    public String myInformation(@AuthenticationPrincipal UserDetails userDetails, Model model){

        //Generate the needed lists
        User logedInUser = userRepository.findByUsername(userDetails.getUsername()).orElse(null);

        List<TutorialViewDTO> submittedByMeTutorialsAsView = tutoringService.findByAddedById(logedInUser.getId());

        double averagePrice = 0;
        for(TutorialViewDTO tut:submittedByMeTutorialsAsView){
            averagePrice += tut.getPrice();
        }
        averagePrice = averagePrice/submittedByMeTutorialsAsView.size();
        averagePrice = Math.round(averagePrice*100)/100.0;

        //Insert the lists in the home html
        model.addAttribute("submittedByMeTutorialsAsView", submittedByMeTutorialsAsView);
        model.addAttribute("userName", logedInUser.getUsername());
        model.addAttribute("userEmail", logedInUser.getEmail());
        model.addAttribute("numberOfClasses", submittedByMeTutorialsAsView.size());
        model.addAttribute("averagePrice", averagePrice);

        return "my-information";
    }


}
