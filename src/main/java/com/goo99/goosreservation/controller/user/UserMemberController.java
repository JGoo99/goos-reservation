package com.goo99.goosreservation.controller.user;

import com.goo99.goosreservation.data.dto.UserJoinDto;
import com.goo99.goosreservation.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserMemberController {

  private final UserServiceImpl userService;
  private final Logger LOGGER = LoggerFactory.getLogger(UserMemberController.class);

  @GetMapping("/join")
  public String joinP(Model model) {
    model.addAttribute("errors", new HashMap<>());

    return "user/join";
  }

  @PostMapping("/joinProc")
  public String joinProcP(@Valid @ModelAttribute UserJoinDto userJoinDto,
                          BindingResult bindingResult, Model model) {
    LOGGER.info("[user join]: {}", userJoinDto.toString());

    userService.join(userJoinDto);

    if (bindingResult.hasErrors()) {
      Map<String, String> errors =
        userService.getValidExceptionResult(bindingResult.getAllErrors());
      model.addAttribute("errors", errors);
      return "user/join";
    }

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
