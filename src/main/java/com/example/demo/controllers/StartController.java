package com.example.demo.controllers;

import com.example.demo.entities.EntityA;
import com.example.demo.repository.EntityARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StartController {

    @Autowired
    private EntityARepository entityARepository;

    @RequestMapping(value = "api/entitya", method = RequestMethod.POST)
    public ResponseEntity addEntityA(@RequestParam("data") String data){

        //create entity with auto generated UUID by postgres
        entityARepository.save(new EntityA(data));

        return new ResponseEntity(HttpStatus.OK);
    }
}
