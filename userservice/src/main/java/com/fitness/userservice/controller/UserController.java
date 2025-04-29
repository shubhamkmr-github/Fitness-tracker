package com.fitness.userservice.controller;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId) {
        return new ResponseEntity<>(userService.getUserProfile(userId), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
        return new ResponseEntity<UserResponse>(userService.register(request), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.existByUserId(userId));
    }



}
