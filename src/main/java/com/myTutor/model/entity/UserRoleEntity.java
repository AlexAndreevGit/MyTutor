package com.myTutor.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="roles")
public class UserRoleEntity extends BaseEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @NotNull
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    //Constructor


    public UserRoleEntity() {
    }

    public UserRoleEntity(UserRoleEnum role) {
        this.role = role;
    }

    //Getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }
}
