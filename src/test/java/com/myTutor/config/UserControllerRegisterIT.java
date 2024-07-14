package com.myTutor.config;

import com.myTutor.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerRegisterIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    //IT3
    @Test
    void testRegistation() throws Exception {

        mockMvc.perform(post("/users/register")
                .param("username", "user1")
                .param("email","u@w")
                .param("password","12345")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));

//        Optional<User> user = userRepository.findByUsername("user1");
//
//        Assertions.assertTrue(user.isPresent());
//
//        User user1 =user.get();
//
//        Assertions.assertEquals("u@w",user1.getEmail());

    }




}
