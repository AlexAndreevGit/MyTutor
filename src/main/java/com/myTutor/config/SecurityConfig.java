package com.myTutor.config;

import com.myTutor.repo.UserRepository;
import com.myTutor.service.impl.MyTutorUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //SpringSecurity_1 ->
    //Creating a "SecurityFilterChain"
    //With HttpSecurity we can easily create security filer chain. It is a builder for the class "SecurityFilterChain"
    //With the fluent-API it is convenient to make the configuration
    //We cant debug the fluent-API

    @Bean    //Expose the "SecurityFilterChain" as a bean. Spring takes it and puts it as a filter in the filter chain
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(                    //Section 1 -> .authorizeHttpRequests()   setup which URL-s are available for which user
                    authorizeRequests ->
                            authorizeRequests
                                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() //all static resources(CSS,images, JS) are accessible for everyone
                                    .requestMatchers("/", "users/login", "users/register").permitAll()          //accessible for all users
                                    .anyRequest()       //for all other requests
                                    .authenticated()    //we need an authenticated user.
                )
                .formLogin(formLogin ->                               //Section 2 -> .formLogin()
                        formLogin
                                .loginPage("/users/login")     //our custom login form
                                .usernameParameter("username") //The name of the username parameter
                                .passwordParameter("password") //The name of the password parameter
                                .defaultSuccessUrl("/",true)  // if the login is successful
                                .failureForwardUrl("/users/login-error")               // client side redirect, if the login fails    failerURL- server side
                )
                .logout(                                                //Section 3 -> .logout()
                        logout ->
                                logout.logoutUrl("/users/logout")       // the logout URL . It is a POST request. POST because of the CSRF token. The CSRF token provide protection.
                                        .logoutSuccessUrl("/")          // where to go after successful logout
                                        .invalidateHttpSession(true)    //invalidate session after that

                )
                .build();                                               //Section 4 -> call the build-method at the end
    }

    //SpringSecurity_3 -> we are exposing "MyTutorUserDetailsService" as a bean
    //SpringSecurity_4 -> By this library(springsecurity6) we get additional extras that we don't have to implement ourself
    @Bean
    public MyTutorUserDetailsService userDetailsService(UserRepository userRepository){
        return new MyTutorUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder
                .defaultsForSpringSecurity_v5_8();
    }

}
