package com.myTutor.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name= "tutoringOffer")
public class TutoringOffer extends BaseEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    @Positive
    private Double price;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User addedBy;

    //Constructor
    public TutoringOffer() {

    }

    public TutoringOffer(String name, String description, Double price, Category category, User addedBy) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.addedBy = addedBy;
    }

    //Getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
