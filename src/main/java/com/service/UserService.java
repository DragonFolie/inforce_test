package com.service;

import com.entity.User;
import com.dto.UserDto;

import java.util.List;


public interface UserService {

    void saveUser(UserDto userDto);
    User findByEmail(String email);
    List<UserDto> findAllUsers();
    User updateUser(User user);
}
