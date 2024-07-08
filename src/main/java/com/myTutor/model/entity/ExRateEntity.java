package com.myTutor.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name= "ex_rates")
public class ExRateEntity extends BaseEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String currency;

    @Positive
    @NotNull
    private BigDecimal rate;

    //Getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

//    @Override
//    public String toString() {
//        return "ExRateEntity{" +
//                ", currency='" + currency + '\'' +
//                ", rate=" + rate +
//                '}';
//    }


    @Override
    public String toString() {
        return "ExRateEntity{" +
                "currency='" + currency + '\'' +
                ", rate=" + rate +
                '}';
    }

}
