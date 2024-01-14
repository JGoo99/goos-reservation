package com.goo99.goosreservation.service;

import com.goo99.goosreservation.data.dto.TaxiAddDto;
import com.goo99.goosreservation.data.dto.TaxiInfoDto;

public interface TaxiService {
  TaxiInfoDto add(TaxiAddDto taxiAddDto);

}
