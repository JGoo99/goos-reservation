package com.goo99.goosreservation.service;

import com.goo99.goosreservation.data.dto.PagingDto;
import com.goo99.goosreservation.data.dto.TaxiAddDto;
import com.goo99.goosreservation.data.dto.TaxiInfoDto;
import org.springframework.data.domain.Page;

public interface TaxiService {
  TaxiInfoDto add(TaxiAddDto taxiAddDto);

  Page<TaxiInfoDto> getList(PagingDto pagingDto);
}
