package com.myTutor.service.impl;

import com.myTutor.model.entity.Category;
import com.myTutor.model.enums.CategoryNameEnum;
import com.myTutor.repo.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    //We mock the dependencies
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    //We use the @InjectMocks annotation to create an instance of UserService and inject the mocks into it.
    private CategoryServiceImpl categoryService;

    private Category category;

    @BeforeEach
    public void setUp() {

    category= new Category();
    category.setName(CategoryNameEnum.MATHEMATICS);
    category.setDescription("Description");

    }

    @Test
    void initCategories() {

        categoryService.initCategories();

        verify(categoryRepository, times(1)).count();

    }
}