package com.goo99.goosreservation.controller.user;

import com.goo99.goosreservation.data.dto.PagingDto;
import com.goo99.goosreservation.data.dto.ReservationInfoDto;
import com.goo99.goosreservation.data.dto.StudioInfoDto;
import com.goo99.goosreservation.data.dto.user.UserCustomDetails;
import com.goo99.goosreservation.data.dto.user.UserJoinDto;
import com.goo99.goosreservation.service.impl.ReservationServiceImpl;
import com.goo99.goosreservation.service.impl.StudioServiceImpl;
import com.goo99.goosreservation.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserMemberController {

  private final UserServiceImpl userService;
  private final StudioServiceImpl studioService;
  private final ReservationServiceImpl reservationService;

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
  public String loginP(@RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "exception", required = false) String exception,
                       Model model) {

    model.addAttribute("error", error);
    model.addAttribute("exception", exception);

    return "user/login";
  }

  @GetMapping("/home")
  public String homeP(@RequestParam(defaultValue = "1") int page,
                      @ModelAttribute PagingDto pagingDto,
                      @PageableDefault(page = 1) Pageable pageable, Model model) {

    pagingDto.setPageNum(page);
    Page<StudioInfoDto> list = studioService.getList(pagingDto);
    pagingDto.setPagingDto(pageable, list.getTotalPages());

    model.addAttribute("list", list);
    model.addAttribute("startPage", pagingDto.getStartPage());
    model.addAttribute("endPage", pagingDto.getEndPage());

    return "user/home";
  }

  @GetMapping("/my")
  public String myP(@RequestParam(defaultValue = "1") int page,
                    @ModelAttribute PagingDto pagingDto,
                    @PageableDefault(page = 1) Pageable pageable, Model model,
                    @AuthenticationPrincipal UserCustomDetails details) {

    pagingDto.setPageNum(page);
    pagingDto.setSize(7);
    pagingDto.setDirectionColumn("reservedAt");
    Page<ReservationInfoDto> list = reservationService.getListByUserId(details.getId(), pagingDto);
    pagingDto.setPagingDto(pageable, list.getTotalPages());

    model.addAttribute("list", list);
    model.addAttribute("startPage", pagingDto.getStartPage());
    model.addAttribute("endPage", pagingDto.getEndPage());

    return "user/my/my";
  }
}
