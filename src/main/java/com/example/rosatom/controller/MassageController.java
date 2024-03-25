package com.example.rosatom.controller;

import com.example.rosatom.model.AddMassModel;
import com.example.rosatom.model.DelMassModel;
import com.example.rosatom.model.MassageModel;
import com.example.rosatom.model.UserModel;
import com.example.rosatom.service.MassageService;
import com.example.rosatom.service.TopicService;
import com.example.rosatom.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/massages")
@Tag(name = "Контроллер сообщений",description = "Управляет созданием/чтением/редактированием/удалением сообщений")
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
    public ResponseEntity deleteMassage(@RequestBody DelMassModel massageModel) {
        try {
            massageService.removeMassage(massageModel);
            return ResponseEntity.ok().body("Ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PostMapping()
    public ResponseEntity addMassage(@RequestBody AddMassModel massageModel) {
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
    @GetMapping("/{massageId}")
    public ResponseEntity readMess(@PathVariable Long massageId){
        try {
            return ResponseEntity.ok().body(massageService.readMassage(massageId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping()
    public ResponseEntity readAllMess(){
        try {
            return ResponseEntity.ok().body(massageService.readAllMassage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
