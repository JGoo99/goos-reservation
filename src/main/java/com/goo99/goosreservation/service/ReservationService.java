package com.goo99.goosreservation.service;

import com.goo99.goosreservation.data.dto.ReservationInfoDto;
import com.goo99.goosreservation.data.dto.user.ReservationAddDto;

import java.util.Queue;

public interface ReservationService {
  Queue<Integer> getAvailableTimeList(ReservationAddDto addDto);

  void setOpeningHours(ReservationAddDto addDto);

  ReservationInfoDto save(ReservationAddDto addDto);
}
