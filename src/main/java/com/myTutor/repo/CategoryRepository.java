package com.myTutor.repo;

import com.myTutor.model.entity.Category;
import com.myTutor.model.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findByName(CategoryNameEnum categoryNameEnum);

}
