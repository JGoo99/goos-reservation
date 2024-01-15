package com.goo99.goosreservation.controller.owner;

import com.goo99.goosreservation.data.dto.owner.StudioAddDto;
import com.goo99.goosreservation.data.dto.StudioInfoDto;
import com.goo99.goosreservation.service.impl.StudioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owner/studio")
public class OwnerStudioController {

  private final StudioServiceImpl studioService;

  @GetMapping("/add")
  public String addP() {

    return "owner/studio/add";
  }

  @PostMapping("/addProc")
  public String addProcP(@ModelAttribute StudioAddDto studioAddDto, Model model) {

    StudioInfoDto studioInfoDto = studioService.add(studioAddDto);
    model.addAttribute("studioInfo", studioInfoDto);

    return "owner/studio/add-success";
  }

  @GetMapping("/info")
  public String detailP(@RequestParam Long studioId, Model model) {

    StudioInfoDto studioInfo = studioService.getInfo(studioId);
    model.addAttribute("studioInfo", studioInfo);

    return "owner/studio/info";
  }

  @GetMapping("/edit")
  public String editP(@RequestParam Long studioId, Model model) {

    StudioInfoDto studioInfo = studioService.getInfo(studioId);
    model.addAttribute("studioInfo", studioInfo);

    return "owner/studio/edit";
  }
}
