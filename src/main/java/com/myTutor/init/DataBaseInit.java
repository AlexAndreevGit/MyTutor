package com.myTutor.init;


import com.myTutor.service.CategoryService;
import com.myTutor.service.InitDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// CommandLinerRunneris coming from spring
// The interface contains only the run method
@Component
public class DataBaseInit implements CommandLineRunner {

    private final CategoryService categoryService;
    private final InitDataService initDataService;

    public DataBaseInit(CategoryService categoryService, InitDataService initDataService) {
        this.categoryService = categoryService;
        this.initDataService = initDataService;

    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.initCategories();
        initDataService.initData();

    }

}
