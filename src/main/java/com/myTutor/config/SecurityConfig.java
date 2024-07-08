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

    //creating a securityFilterChanin with filters
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
                //setup which URL-s are available for which user
                    authorizeRequests ->
                            authorizeRequests
                                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() //all static resources are accessible for everyone
                                    .requestMatchers("/", "users/login", "users/register").permitAll() //accessible for all users
                                    .anyRequest()
                                    .authenticated() // for everything else we need an authenticated user.
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/users/login") // ouer login form
                                .usernameParameter("username") //username parameter
                                .passwordParameter("password") //password parameter
                                .defaultSuccessUrl("/",true) // if the login is sucessfull
                                .failureForwardUrl("/users/login-error") // if teh login fails
                )
                .logout(
                        logout ->
                                logout.logoutUrl("/users/logout")  // the logout URL
                                        .logoutSuccessUrl("/")     // where to go after successful logout
                                        .invalidateHttpSession(true)   //invalidate session after that

                )
                .build();
    }

    // we are exposing "MyTutorUserDetailsService" as a bean
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
