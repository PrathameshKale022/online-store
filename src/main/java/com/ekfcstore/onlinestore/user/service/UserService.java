package com.ekfcstore.onlinestore.user.service;

import com.ekfcstore.onlinestore.user.dto.LoginDto;
import com.ekfcstore.onlinestore.user.dto.UserDto;

import java.util.List;

public interface UserService {

     UserDto addUser(UserDto user);

     List<UserDto> getAllUsers();

     UserDto getUserById(Long id);

     UserDto getUserByUserName(String name);

     void deleteUserById(Long id);





}
