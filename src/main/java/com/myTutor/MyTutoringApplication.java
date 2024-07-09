package com.myTutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//Spring is starting the class under that annotation and start surcharging for the controllers(@Controller)
@SpringBootApplication
public class MyTutoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyTutoringApplication.class, args);
    }
}
