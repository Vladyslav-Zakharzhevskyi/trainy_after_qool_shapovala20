package com.example.demo.controller;


import com.example.demo.entity.mappedsuperclass.BaseActivity;
import com.example.demo.entity.mappedsuperclass.Development;
import com.example.demo.entity.mappedsuperclass.MeetFriend;
import com.example.demo.entity.mappedsuperclass.WatchTV;
import com.example.demo.repository.BaseActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    private BaseActionRepository<BaseActivity> activityBaseActionRepository;


    @RequestMapping(value = "/")
    public String welcomeMessage() {
        return "home";
    }

    @RequestMapping(value = "/api/initactivity", method = RequestMethod.POST)
    public ResponseEntity<String> initActivity() {
        WatchTV watchTV = new WatchTV("Dva Papi", 2.00);
        activityBaseActionRepository.save(watchTV);

        Development development = new Development("Java", "LapTop", 7.00);
        activityBaseActionRepository.save(development);

        MeetFriend meetFriend = new MeetFriend("Andriy", "Oleni Teligi 15", 2.5);
        activityBaseActionRepository.save(meetFriend);

        return ResponseEntity.ok("Default MappedSuperclass created!");
    }

}
