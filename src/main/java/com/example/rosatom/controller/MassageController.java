package com.example.rosatom.controller;

import com.example.rosatom.model.MassageModel;
import com.example.rosatom.service.MassageService;
import com.example.rosatom.service.TopicService;
import com.example.rosatom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RepositoryRestController("/massages")
public class MassageController {
    MassageService massageService;
    TopicService topicService;
    UserService userService;
    @Autowired
    public MassageController(MassageService masageSrvice, TopicService topicService, UserService userService) {
        this.massageService = masageSrvice;
        this.topicService = topicService;
        this.userService = userService;
    }

    @DeleteMapping()
    public ResponseEntity deleteMassage(@RequestBody MassageModel massageModel) {
        try {
            massageService.removeMassage(massageModel);
            return ResponseEntity.ok().body("Ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PostMapping()
    public ResponseEntity addMassage(@RequestBody MassageModel massageModel) {
        try {
            massageService.addMassage(massageModel);
            return ResponseEntity.ok().body("Ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PutMapping()
    public ResponseEntity editMassage(@RequestBody MassageModel massageModel){
        try {
            massageService.editMassage(massageModel);
            return ResponseEntity.ok().body("Ok");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
