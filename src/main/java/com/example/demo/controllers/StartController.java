package com.example.demo.controllers;

import com.example.demo.entities.mappedsuperclass.BaseActivity;
import com.example.demo.entities.mappedsuperclass.Development;
import com.example.demo.entities.mappedsuperclass.MeetFriend;
import com.example.demo.entities.mappedsuperclass.WatchTV;
import com.example.demo.repository.BaseActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("api/")
public class StartController {

    @Autowired
    private BaseActionRepository<BaseActivity> activityBaseActionRepository;

    @RequestMapping(value = "initactivity", method = RequestMethod.POST)
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
