package com.service;

import com.dto.UserDto;
import com.entity.User;
import java.util.List;


public interface UserService {

  void saveUser(UserDto userDto);

  User findByEmail(String email);

  List<UserDto> findAllUsers();

  void updateUser(String field, String search, String value);
}
