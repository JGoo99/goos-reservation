package com.goo99.goosreservation.controller.owner;

import com.goo99.goosreservation.data.dto.PagingDto;
import com.goo99.goosreservation.data.dto.StudioInfoDto;
import com.goo99.goosreservation.data.dto.owner.OwnerDetails;
import com.goo99.goosreservation.data.dto.owner.OwnerJoinDto;
import com.goo99.goosreservation.service.impl.OwnerServiceImpl;
import com.goo99.goosreservation.service.impl.StudioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerMemberController {

  private final OwnerServiceImpl ownerService;
  private final StudioServiceImpl studioService;
  private final Logger LOGGER = LoggerFactory.getLogger(OwnerMemberController.class);

  @GetMapping("/join")
  public String joinP() {

    return "owner/join";
  }

  @PostMapping("/joinProc")
  public String joinProcP(@ModelAttribute OwnerJoinDto ownerJoinDto) {

    LOGGER.info("[owner join]: {}", ownerJoinDto.toString());
    ownerService.join(ownerJoinDto);

    return "redirect:/owner/login";
  }

  @GetMapping("/login")
  public String loginP(@RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "exception", required = false) String exception,
                       Model model) {

    model.addAttribute("error", error);
    model.addAttribute("exception", exception);

    return "owner/login";
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

    return "owner/home";
  }
}
