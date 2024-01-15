package com.goo99.goosreservation.service;

import com.goo99.goosreservation.data.dto.PagingDto;
import com.goo99.goosreservation.data.dto.StudioInfoDto;
import com.goo99.goosreservation.data.dto.owner.StudioAddDto;
import com.goo99.goosreservation.data.dto.owner.StudioEditDto;
import org.springframework.data.domain.Page;

public interface StudioService {
  StudioInfoDto add(StudioAddDto studioAddDto);

  Page<StudioInfoDto> getList(PagingDto pagingDto);

  StudioInfoDto getInfo(Long studioId);

  Page<StudioInfoDto> getListByOwnerId(Long ownerId, PagingDto pagingDto);

  void deleteById(Long studioId);

  StudioInfoDto edit(StudioEditDto studioEditDto);
}
