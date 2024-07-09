package com.myTutor.model.entity;

import com.myTutor.model.enums.CategoryNameEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "TutoringCategories")
public class Category extends BaseEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum name;

    @Column
    private String description;

//    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
//    private List<TutoringOffer> tutoringOffers;

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

//    public List<TutoringOffer> getRecipes() {
//        return tutoringOffers;
//    }
//
//    public void setRecipes(List<TutoringOffer> tutoringOffers) {
//        this.tutoringOffers = tutoringOffers;
//    }

}
