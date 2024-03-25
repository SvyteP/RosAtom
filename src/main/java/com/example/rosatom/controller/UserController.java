package com.example.rosatom.controller;

import com.example.rosatom.model.UserModel;
import com.example.rosatom.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "Контроллер пользователей",description = "Отвечает за работу с пользователями")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping()
    public ResponseEntity createUser(@RequestBody String name){
        try {
            userService.createUser(name);
            return ResponseEntity.ok().body("User was created!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping()
    public ResponseEntity readAllUsers(){
        try {

            return ResponseEntity.ok().body(userService.readAllUsers());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId){
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok().body("User was deleted!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{userId}")
    public ResponseEntity readUser(@PathVariable Long userId){
        try {

            return ResponseEntity.ok().body(userService.readUser(userId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping()
    public ResponseEntity editUser(@RequestBody UserModel userModel){
        try {
            userService.editUser(userModel);
            return ResponseEntity.ok().body("User was edited!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
