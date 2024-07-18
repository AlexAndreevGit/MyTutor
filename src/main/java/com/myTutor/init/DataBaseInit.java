package com.myTutor.init;


import com.myTutor.service.CategoryService;
import com.myTutor.service.InitDataService;
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
    private final InitDataService initDataService;

    public DataBaseInit(CategoryService categoryService, UserService userService, TutoringService tutoringService, InitDataService initDataService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.tutoringService = tutoringService;
        this.initDataService = initDataService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.initCategories();
        initDataService.initData();
        //userService.initUsers();
        //tutoringService.initTutoringOffers();

    }

}
