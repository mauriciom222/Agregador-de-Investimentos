package com.invest.agregadorinvestimento.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invest.agregadorinvestimento.dto.CreateUserDto;
import com.invest.agregadorinvestimento.dto.UpdateUserDTO;
import com.invest.agregadorinvestimento.entity.User;
import com.invest.agregadorinvestimento.service.UserService;

import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
@RequestMapping("/v1/users")

public class UserController {
    
    private UserService userService;
    

    /* 
    public UserController(UserService userService) {
        this.userService = userService;
    }
    */

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {

        var userId = userService.createUser(createUserDto);

        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        var User = userService.getUserById(userId);
        if (User.isPresent()) {
            return ResponseEntity.ok(User.get());
        }else {
            return ResponseEntity.notFound().build();
        }
        
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        var users = userService.listUsers();

        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserbyId(@PathVariable("userId") String userId, 
                                               @RequestBody UpdateUserDTO updateUserDTO) {
        userService.updateUserbyId(userId, updateUserDTO);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserbyId(@PathVariable("userId") String userId) {

        userService.deleteUser(userId);

         return ResponseEntity.noContent().build();
    }


}
