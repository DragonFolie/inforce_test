package com.controller;

import com.dto.UserDto;
import com.entity.User;
import com.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

  private UserService userService;


  public AuthController(UserService userService) {
    this.userService = userService;
  }


  @GetMapping("index")
  public String home() {
    return "index";
  }


  @GetMapping("/login")
  public String loginForm() {
    return "login";
  }


  @GetMapping("register")
  public String showRegistrationForm(Model model) {
    UserDto user = new UserDto();
    model.addAttribute("user", user);
    return "register";
  }


  @GetMapping("/users")
  public String listRegisteredUsers(Model model) {
    List<UserDto> users = userService.findAllUsers();
    model.addAttribute("users", users);
    return "users";
  }


  @PostMapping("/register/save")
  public String registration(@Valid @ModelAttribute("user") UserDto user,
                             BindingResult result,
                             Model model) {
    User existing = userService.findByEmail(user.getEmail());
    if (existing != null) {
      result.rejectValue("email", null, "There is already an account registered with that email");
    }
    if (result.hasErrors()) {
      model.addAttribute("user", user);
      return "register";
    }
    userService.saveUser(user);
    return "redirect:/register?success";
  }


}