package com.myTutor.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "Tutors")
public class User extends BaseEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER) //@ManyToMany is "lasy" by default. We change it to "eager" so they are fetch all at once
    @JoinTable(
            name="user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<UserRoleEntity> roles =new ArrayList<>();

//    @OneToMany(mappedBy = "addedBy", fetch = FetchType.EAGER)
//    private List<TutoringOffer> addedTutoringOffers;
//
//    @OneToMany(mappedBy = "addedBy", fetch = FetchType.EAGER)
//    private List<TutoringOffer> favouriteTutoringOffers;

    //Constructor
    public User() {

    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


    //Getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public List<TutoringOffer> getAddedRecipes() {
//        return addedTutoringOffers;
//    }
//
//    public void setAddedRecipes(List<TutoringOffer> addedTutoringOffers) {
//        this.addedTutoringOffers = addedTutoringOffers;
//    }
//
//    public List<TutoringOffer> getFavouriteRecipes() {
//        return favouriteTutoringOffers;
//    }
//
//    public void setFavouriteRecipes(List<TutoringOffer> favouriteTutoringOffers) {
//        this.favouriteTutoringOffers = favouriteTutoringOffers;
//    }
//
//    public void addFavorite(TutoringOffer tutoringOffer) {
//
//        this.favouriteTutoringOffers.add(tutoringOffer);
//
//    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
    }
}
