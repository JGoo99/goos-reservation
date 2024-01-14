package com.goo99.goosreservation.controller.user;

import com.goo99.goosreservation.data.dto.TaxiInfoDto;
import com.goo99.goosreservation.service.impl.TaxiServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/taxi")
public class UserTaxiController {

  private final TaxiServiceImpl taxiService;

  @GetMapping("/info")
  public String detailP(@RequestParam Long taxiId, Model model) {

    TaxiInfoDto taxiInfo = taxiService.getInfo(taxiId);
    model.addAttribute("taxiInfo", taxiInfo);

    return "user/taxi/info";
  }

}
