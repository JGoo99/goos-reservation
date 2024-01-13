package com.goo99.goosreservation.controller.user;

import com.goo99.goosreservation.data.dto.UserJoinDto;
import com.goo99.goosreservation.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserMemberController {

  private final UserServiceImpl userService;
  private final Logger LOGGER = LoggerFactory.getLogger(UserMemberController.class);

  @GetMapping("/join")
  public String joinP() {

    return "user/join";
  }

  @PostMapping("/joinProc")
  public String joinProcP(@ModelAttribute UserJoinDto userJoinDto) {

    LOGGER.info("[user join]: {}", userJoinDto.toString());
    userService.join(userJoinDto);

    return "redirect:/login";
  }

  @GetMapping("/login")
  public String loginP() {

    return "user/login";
  }

  @GetMapping("/home")
  public String homeP() {

    return "user/home";
  }
}
