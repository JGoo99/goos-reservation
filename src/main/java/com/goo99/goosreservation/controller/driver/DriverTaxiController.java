package com.goo99.goosreservation.controller.driver;

import com.goo99.goosreservation.data.dto.TaxiAddDto;
import com.goo99.goosreservation.data.dto.TaxiInfoDto;
import com.goo99.goosreservation.service.impl.TaxiServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/driver/taxi")
public class DriverTaxiController {

  private final TaxiServiceImpl taxiService;

  @GetMapping("/add")
  public String addP() {

    return "driver/taxi/add";
  }

  @PostMapping("/addProc")
  public String addProcP(@ModelAttribute TaxiAddDto taxiAddDto, Model model) {

    TaxiInfoDto taxiInfoDto = taxiService.add(taxiAddDto);
    model.addAttribute("taxiInfo", taxiInfoDto);

    return "driver/taxi/add-success";
  }

  @GetMapping("/info")
  public String detailP(@RequestParam Long taxiId, Model model) {

    TaxiInfoDto taxiInfo = taxiService.getInfo(taxiId);
    model.addAttribute("taxiInfo", taxiInfo);

    return "driver/taxi/info";
  }
}
