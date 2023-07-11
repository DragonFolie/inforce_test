package com.service.impl;

import com.dto.UserDto;
import com.entity.Role;
import com.entity.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.service.UserService;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;


  @Override
  public List<UserDto> findAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream().map((user) -> convertEntityToDto(user))
        .collect(Collectors.toList());
  }


  @Override
  public User findByEmail(String email) {

    return userRepository.findByEmail(email);
  }


  @Override
  public void saveUser(UserDto userDto) {

    User user = User.builder()
        .firstName(userDto.getFirstName())
        .lastName(userDto.getLastName())
        .email(userDto.getEmail())
        .country(userDto.getCountry())
        .password(passwordEncoder.encode(userDto.getPassword()))
        .build();

    Role role = roleRepository.findByName("ROLE_ADMIN");
    if (role == null) {
      role = checkRoleExist();
    }
    user.setRoles(Arrays.asList(role));
    userRepository.save(user);
  }


  @Override
  public void updateUser(String field, String search, String value) {
    final boolean exists = userRepository.existsByEmail(search);
    if (exists) {
      if (field.equals("firstName")) {
        userRepository.updateFirstNameField(value, search);
      }
      if (field.equals("lastName")) {
        userRepository.updateLastNameField(value, search);
      }
      if (field.equals("country")) {
        userRepository.updateCountryNameField(value, search);
      }
    }
  }



  private UserDto convertEntityToDto(User user) {
    return UserDto
        .builder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .country(user.getCountry())
        .password(user.getPassword())
        .build();
  }

  private Role checkRoleExist() {
    Role role = Role.builder()
            .name("ROLE_ADMIN")
                .build();
    return roleRepository.save(role);
  }
}
