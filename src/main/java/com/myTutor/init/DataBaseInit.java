package com.myTutor.init;


import com.myTutor.service.CategoryService;
import com.myTutor.service.TutoringService;
import com.myTutor.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// CommandLinerRunneris coming from spring
// The interface contains only the run method
@Component
public class DataBaseInit implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserService userService;
    private final TutoringService tutoringService;

    public DataBaseInit(CategoryService categoryService, UserService userService, TutoringService tutoringService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.tutoringService = tutoringService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.initCategories();
        userService.initUsers();
        //tutoringService.initTutoringOffers();

    }

}
