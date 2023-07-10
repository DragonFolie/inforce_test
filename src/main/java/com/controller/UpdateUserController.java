package com.controller;

import com.entity.User;
import com.dto.UserDto;
import com.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpdateUserController {

  private UserService userService;

  public UpdateUserController(UserService userService) {
    this.userService = userService;
  }


  @GetMapping("/update")
  public String showUpdateForm(Model model) {
    UserDto user = new UserDto();
    model.addAttribute("user", new User());
    return "update_page";
  }

  @PostMapping("/update")
  public String updateUser(User user) {
    userService.updateUser(user);
    return "redirect:/index";
  }


}
