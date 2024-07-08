package com.myTutor.model.DTOs;

public class TutorialViewDTO {

    private Long id;

    private String name;

    private String description ;

    private Double price;

    private String emailOfTheTutor;

    private Double price2;

    //Constructor
    public TutorialViewDTO() {

    }

    //Geyyets and setters
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice2() {
        return price2;
    }

    public void setPrice2(Double price2) {
        this.price2 = price2;
    }

    public String getEmailOfTheTutor() {
        return emailOfTheTutor;
    }

    public void setEmailOfTheTutor(String emailOfTheTutor) {
        this.emailOfTheTutor = emailOfTheTutor;
    }
}
