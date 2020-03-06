package com.example.demo.controllers;

import com.example.demo.entities.EntityA;
import com.example.demo.entities.mappedsuperclass.BaseActivity;
import com.example.demo.entities.mappedsuperclass.MeetFriend;
import com.example.demo.entities.mappedsuperclass.WatchTV;
import com.example.demo.entities.mappedsuperclass.Development;
import com.example.demo.repository.BaseActionRepository;
import com.example.demo.repository.EntityARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class StartController {

    @Autowired
    private EntityARepository entityARepository;

    @Autowired
    private BaseActionRepository<BaseActivity> activityBaseActionRepository;

    @RequestMapping(value = "api/entitya", method = RequestMethod.POST)
    public ResponseEntity addEntityA(@RequestParam("data") String data) {

        //create entity with auto generated UUID by postgres
        entityARepository.save(new EntityA(data));

        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseStatus(code = HttpStatus.OK, value = HttpStatus.OK, reason = "EXECUTED")
    @RequestMapping(value = "api/initactivity", method = RequestMethod.POST)
    public void initActivity() {
        WatchTV watchTV = new WatchTV("Dva Papi", 2.00);
        activityBaseActionRepository.save(watchTV);

        Development development = new Development("Java", "LapTop", 7.00);
        activityBaseActionRepository.save(development);

        MeetFriend meetFriend = new MeetFriend("Andriy", "Oleni Teligi 15", 2.5);
        activityBaseActionRepository.save(meetFriend);

    }


}
