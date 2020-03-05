package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartController {


    @RequestMapping(value = "/")
    public String welcomeMessage(Model model) {
        model.addAttribute("message", "Spring Boot Application, created from spring Intlzr.");
        return "home";
    }
}
