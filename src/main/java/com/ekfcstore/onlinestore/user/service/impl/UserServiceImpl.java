package com.ekfcstore.onlinestore.user.service.impl;

import com.ekfcstore.onlinestore.user.dto.LoginDto;
import com.ekfcstore.onlinestore.user.dto.UserDto;
import com.ekfcstore.onlinestore.user.entity.User;
import com.ekfcstore.onlinestore.user.repo.UserRepository;
import com.ekfcstore.onlinestore.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final ModelMapper modelMapper;
    final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper,BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto addUser(UserDto userdto) {

        User user = modelMapper.map(userdto, User.class);
        String hashedpassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedpassword);
        User saveduser = userRepository.save(user);
        UserDto saveduserdto = modelMapper.map(saveduser, UserDto.class);
        return saveduserdto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(new User());
        UserDto userdto = modelMapper.map(user, UserDto.class);
        return userdto;
    }

    @Override
    public UserDto getUserByUserName(String name) {
        User user = userRepository.findByUsername("");
        UserDto userdto = modelMapper.map(user, UserDto.class);
        return userdto;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }




}
