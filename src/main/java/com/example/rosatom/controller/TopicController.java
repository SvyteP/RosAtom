package com.example.rosatom.controller;

import com.example.rosatom.model.AddTopicModel;
import com.example.rosatom.model.EditTopicModel;
import com.example.rosatom.model.DelTopicModel;
import com.example.rosatom.service.TopicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/topics")
@Tag(name = "Контроллер топиков",description = "Управляет созданием/чтением/редактированием/удалением топиков")
public class TopicController {
    private final TopicService topicService;
    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping()
    public ResponseEntity addTopic(@RequestBody AddTopicModel topicModel){
        try {
            topicService.createNewTopic(topicModel);
            return ResponseEntity.ok().body("Topic was created!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity delTopic(@RequestBody DelTopicModel topicModel){
        try {
            topicService.removeTopic(topicModel);
            return ResponseEntity.ok().body("Topic was deleted");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{topicId}")
    public ResponseEntity readAllMess(@PathVariable Long topicId){
        try {
            return ResponseEntity.ok().body(topicService.readAllMassage(topicId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity editTopic(@RequestBody EditTopicModel editTopicModel){
        try {
            topicService.editTopic(editTopicModel);
            return ResponseEntity.ok().body("Ok!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping()
    public ResponseEntity getTopic(){
        try {

            return ResponseEntity.ok().body(topicService.readAllTopics());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
