package com.goo99.goosreservation.controller.driver;

import com.goo99.goosreservation.data.dto.DriverJoinDto;
import com.goo99.goosreservation.service.impl.DriverServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DriverMemberController {

  private final DriverServiceImpl driverService;
  private final Logger LOGGER = LoggerFactory.getLogger(DriverMemberController.class);

  @GetMapping("/join")
  public String joinP() {

    return "driver/join";
  }

  @PostMapping("/joinProc")
  public String joinProcP(@ModelAttribute DriverJoinDto driverJoinDto) {

    LOGGER.info("[driver join]: {}", driverJoinDto.toString());
    driverService.join(driverJoinDto);

    return "redirect:/driver/login";
  }

  @GetMapping("/login")
  public String loginP() {

    return "driver/login";
  }

  @GetMapping("/home")
  public String homeP() {

    return "driver/home";
  }
}
