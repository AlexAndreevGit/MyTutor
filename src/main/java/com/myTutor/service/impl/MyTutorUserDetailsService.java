package com.myTutor.service.impl;

import com.myTutor.model.MyTutorUserDetails;
import com.myTutor.model.entity.User;
import com.myTutor.model.entity.UserRoleEnum;
import com.myTutor.repo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

//SpringSecurity_2 -> MyTutorUserDetailsService
// we implement the interface "implements UserDetailsService" -> so we explain to spring how a user looks in oer application
// on purpose no annotation so it doesen't go in the context of spring
public class MyTutorUserDetailsService implements UserDetailsService {

    //we are working with the interface, so we achieve a higher level of abstraction
    private final UserRepository userRepository;

    public MyTutorUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //when we implement "UserDetailsService" we should be able to load user by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return  userRepository
                .findByUsername(username)
                .map(MyTutorUserDetailsService::map)  //use the map(User user) method
                .orElseThrow(()-> new UsernameNotFoundException("Username with username" + username + "not found!"));   // If not such user found then throw an exception

    }

    private static UserDetails map(User user){
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUsername())
//                .password(user.getPassword())
//                .authorities(List.of())/*TODO*/
//                .disabled(false)
//                .build();


        return new MyTutorUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(r->r.getRole()).map(MyTutorUserDetailsService::map).toList()
        );
    }

    //"GrantedAuthority" is an interface with one method
    private static GrantedAuthority map(UserRoleEnum role){
        return new SimpleGrantedAuthority(
                "ROLE_" + role
        );
    }

}
