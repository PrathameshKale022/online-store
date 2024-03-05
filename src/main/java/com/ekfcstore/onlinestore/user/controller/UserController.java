package com.ekfcstore.onlinestore.user.controller;


import com.ekfcstore.onlinestore.user.dto.LoginDto;
import com.ekfcstore.onlinestore.user.dto.UserDto;
import com.ekfcstore.onlinestore.user.entity.User;
import com.ekfcstore.onlinestore.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/userModule")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<UserDto> CreateUser(@RequestBody UserDto userDto)
    {
        UserDto userDto1 = userService.addUser(userDto);

        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }


    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> findallUsers()
    {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("id") Long user_id)
    {
        return new ResponseEntity<>(userService.getUserById(user_id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long user_id)
    {
        userService.deleteUserById(user_id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }




}
