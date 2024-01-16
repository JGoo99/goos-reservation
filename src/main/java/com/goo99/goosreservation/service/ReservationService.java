package com.goo99.goosreservation.service;

import com.goo99.goosreservation.data.dto.PagingDto;
import com.goo99.goosreservation.data.dto.ReservationInfoDto;
import com.goo99.goosreservation.data.dto.user.ReservationAddDto;
import org.springframework.data.domain.Page;

import java.util.Queue;

public interface ReservationService {
  Queue<Integer> getAvailableTimeList(ReservationAddDto addDto);

  void setOpeningHours(ReservationAddDto addDto);

  ReservationInfoDto save(ReservationAddDto addDto);

  Page<ReservationInfoDto> getAcceptedList(Long ownerId, PagingDto pagingDto);

  void reject(Long ownerId, Long reservationId);

  Page<ReservationInfoDto> getListByUserId(Long userId, PagingDto pagingDto);
}
