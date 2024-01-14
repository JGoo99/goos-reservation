package com.goo99.goosreservation.controller.driver;

import com.goo99.goosreservation.data.dto.DriverJoinDto;
import com.goo99.goosreservation.data.dto.PagingDto;
import com.goo99.goosreservation.data.dto.TaxiInfoDto;
import com.goo99.goosreservation.service.impl.DriverServiceImpl;
import com.goo99.goosreservation.service.impl.TaxiServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DriverMemberController {

  private final DriverServiceImpl driverService;
  private final TaxiServiceImpl taxiService;
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
  public String loginP(@RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "exception", required = false) String exception,
                       Model model) {

    model.addAttribute("error", error);
    model.addAttribute("exception", exception);

    return "driver/login";
  }

  @GetMapping("/home")
  public String homeP(@RequestParam(defaultValue = "1") int page,
                      @ModelAttribute PagingDto pagingDto,
                      @PageableDefault(page = 1) Pageable pageable, Model model) {

    pagingDto.setPageNum(page);
    Page<TaxiInfoDto> list = taxiService.getList(pagingDto);
    pagingDto.setPagingDto(pageable, list.getTotalPages());

    model.addAttribute("list", list);
    model.addAttribute("startPage", pagingDto.getStartPage());
    model.addAttribute("endPage", pagingDto.getEndPage());

    return "driver/home";
  }
}
