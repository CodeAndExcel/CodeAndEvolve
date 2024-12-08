package com.code.evolve.controller;

import com.code.evolve.model.User;
import com.code.evolve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/getUsers")
    public ResponseEntity<?> getAllUsers(){
        List<User> userDetails=userService.getAllUsers();
        if(userDetails.isEmpty()){
            return ResponseEntity.badRequest().body("No Users Found!!");
        }
        return ResponseEntity.ok(userDetails);
    }



}
