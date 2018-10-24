package com.joseph.chip8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class Application extends JFrame {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}