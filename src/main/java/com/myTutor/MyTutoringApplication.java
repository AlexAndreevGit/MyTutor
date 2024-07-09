package com.myTutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


//Spring is starting the class under that annotation and start surcharging for the controllers(@Controller)
@SpringBootApplication
@EnableScheduling
public class MyTutoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyTutoringApplication.class, args);
    }
}
