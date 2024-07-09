package com.myTutor.service.impl;

import com.myTutor.model.entity.Category;
import com.myTutor.model.enums.CategoryNameEnum;
import com.myTutor.repo.CategoryRepository;
import com.myTutor.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    //we are working with the interface so we achieve a higher level of abstraction
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {

        if (categoryRepository.count() != 0) {
            return;
        }

        for (CategoryNameEnum en : CategoryNameEnum.values()) {
            Category category = new Category();
            category.setName(en);

            switch (category.getName()) {
                case MATHEMATICS:
                    category.setDescription("Mathematics is the abstract science of number, quantity, and space, which can be applied to patterns, structures, and changes in the physical world through logical reasoning and quantitative calculations.");
                    break;
                case INFORMATICS:
                    category.setDescription("Informatics is the interdisciplinary study of information processing, management, and dissemination, encompassing the design, implementation, and evaluation of computational systems and their impact on society.");
                    break;
                case DATASCIENCE:
                    category.setDescription("Data science is the multidisciplinary field that combines statistical analysis, machine learning, and domain expertise to extract insights and knowledge from structured and unstructured data.");
                    break;
            }
            categoryRepository.save(category);

        }

    }

}
