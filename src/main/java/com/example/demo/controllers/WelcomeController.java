package com.example.demo.controllers;


import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/")
    public String welcomeMessage(Model model) {
        model.addAttribute("message", "Spring Boot Application, created from spring Intlzr.");
        return "home";
    }

}
