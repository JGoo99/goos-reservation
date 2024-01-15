package com.goo99.goosreservation.controller.user;

import com.goo99.goosreservation.data.dto.ReservationInfoDto;
import com.goo99.goosreservation.data.dto.user.ReservationAddDto;
import com.goo99.goosreservation.data.dto.StudioInfoDto;
import com.goo99.goosreservation.service.impl.ReservationServiceImpl;
import com.goo99.goosreservation.service.impl.StudioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Queue;

@Controller
@RequiredArgsConstructor
@RequestMapping("/studio")
public class UserStudioController {

  private final StudioServiceImpl studioService;
  private final ReservationServiceImpl reservationService;

  @GetMapping("/info")
  public String detailP(@RequestParam Long studioId, Model model) {

    StudioInfoDto studioInfo = studioService.getInfo(studioId);
    model.addAttribute("studioInfo", studioInfo);

    return "user/studio/info";
  }

  @GetMapping("/reserv")
  public String reservP(@RequestParam Long studioId, Model model) {

    model.addAttribute("studioId", studioId);

    return "user/studio/reservation";
  }

  @PostMapping("/reserv.proc1")
  public String reservProc1P(@ModelAttribute ReservationAddDto addDto, Model model) {
    int lastDays =
      LocalDate.of(addDto.getYear(), addDto.getMonth() + 1, 1)
        .minusDays(1).getDayOfMonth();

    model.addAttribute("days", addDto);
    model.addAttribute("lastDays", lastDays);

    return "user/studio/reservation-day";
  }

  @PostMapping("/reserv.proc2")
  public String reservProc2P(@ModelAttribute ReservationAddDto addDto, Model model) {

    Queue<Integer> availableTimes = reservationService.getAvailableTimeList(addDto);

    int lastDays =
      LocalDate.of(addDto.getYear(), addDto.getMonth() + 1, 1)
        .minusDays(1).getDayOfMonth();

    reservationService.setOpeningHours(addDto);

    model.addAttribute("days", addDto);
    model.addAttribute("availableTimes", availableTimes);
    model.addAttribute("lastDays", lastDays);

    return "user/studio/reservation-time";
  }

  @PostMapping("/reserv.proc3")
  public String reservProc3P(@ModelAttribute ReservationAddDto addDto, Model model) {

    ReservationInfoDto infoDto = reservationService.save(addDto);
    model.addAttribute("info", infoDto);

    return "user/studio/reservation-success";
  }

}
