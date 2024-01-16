package com.goo99.goosreservation.controller.owner;

import com.goo99.goosreservation.data.dto.PagingDto;
import com.goo99.goosreservation.data.dto.ReservationInfoDto;
import com.goo99.goosreservation.data.dto.StudioInfoDto;
import com.goo99.goosreservation.data.dto.owner.OwnerDetails;
import com.goo99.goosreservation.data.dto.owner.StudioEditDto;
import com.goo99.goosreservation.service.impl.OwnerServiceImpl;
import com.goo99.goosreservation.service.impl.ReservationServiceImpl;
import com.goo99.goosreservation.service.impl.StudioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owner/my")
public class OwnerMyController {

  private final StudioServiceImpl studioService;
  private final OwnerServiceImpl ownerService;
  private final ReservationServiceImpl reservationService;

  @GetMapping
  public String myP(@RequestParam(defaultValue = "1") int page,
                    @ModelAttribute PagingDto pagingDto,
                    @PageableDefault(page = 1) Pageable pageable, Model model,
                    @AuthenticationPrincipal OwnerDetails details) {

    pagingDto.setPageNum(page);
    Page<StudioInfoDto> list = studioService.getListByOwnerId(details.getId(), pagingDto);
    pagingDto.setPagingDto(pageable, list.getTotalPages());

    model.addAttribute("list", list);
    model.addAttribute("startPage", pagingDto.getStartPage());
    model.addAttribute("endPage", pagingDto.getEndPage());

    return "owner/my/my";
  }

  @GetMapping("/studio")
  public String myStudioP(@RequestParam Long studioId, Model model,
                          @AuthenticationPrincipal OwnerDetails details) {

    ownerService.validateOwner(studioId, details.getId());

    StudioInfoDto studioInfo = studioService.getInfo(studioId);
    model.addAttribute("studioInfo", studioInfo);

    return "owner/my/studio-info";
  }

  @GetMapping("/studio/edit")
  public String myStudioEditP(@RequestParam Long studioId, Model model,
                              @AuthenticationPrincipal OwnerDetails details) {

    ownerService.validateOwner(studioId, details.getId());

    StudioInfoDto studioInfo = studioService.getInfo(studioId);
    model.addAttribute("studioInfo", studioInfo);

    return "owner/my/studio-edit";
  }

  @PostMapping("/studio/editProc")
  public String myStudioEditProcP(@ModelAttribute StudioEditDto studioEditDto, Model model) {

    StudioInfoDto studioInfoDto = studioService.edit(studioEditDto);
    model.addAttribute("studioInfo", studioInfoDto);

    return "owner/my/studio-edit-success";
  }

  @PostMapping("/studio/delete")
  public String myStudioDeleteP(Long studioId,
                                @AuthenticationPrincipal OwnerDetails details) {

    ownerService.validateOwner(studioId, details.getId());
    studioService.deleteById(studioId);

    return "redirect:/owner/my";
  }

  @GetMapping("/reserv")
  public String myReservP(@RequestParam(defaultValue = "1") int page,
                          @ModelAttribute PagingDto pagingDto,
                          @PageableDefault(page = 1) Pageable pageable, Model model,
                          @AuthenticationPrincipal OwnerDetails details) {

    pagingDto.setPageNum(page);
    pagingDto.setSize(7);
    pagingDto.setDirectionColumn("reservedAt");
    Page<ReservationInfoDto> list = reservationService.getAcceptedList(details.getId(), pagingDto);
    pagingDto.setPagingDto(pageable, list.getTotalPages());

    model.addAttribute("list", list);
    model.addAttribute("startPage", pagingDto.getStartPage());
    model.addAttribute("endPage", pagingDto.getEndPage());

    return "owner/my/studio-reserv";
  }

  @PostMapping("/reserv/rejectProc")
  public String myReservRejectProcP(Long reservationId,
                             @AuthenticationPrincipal OwnerDetails details) {

    reservationService.reject(details.getId(), reservationId);
    return "redirect:/owner/my/reserv";
  }
}
