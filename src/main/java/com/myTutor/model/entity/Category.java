package com.myTutor.model.entity;

import com.myTutor.model.enums.CategoryNameEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "TutoringCategories")
public class Category extends BaseEntity{

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum name;

    @Column
    private String description;

    //Constructor
    public Category() {

    }

    public Category(CategoryNameEnum name) {
        this.name = name;
    }

    //Getters and setters

    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
