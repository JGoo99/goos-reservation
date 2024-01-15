package com.goo99.goosreservation.service.impl;

import com.goo99.goosreservation.data.dto.ReservationInfoDto;
import com.goo99.goosreservation.data.dto.user.DateMapping;
import com.goo99.goosreservation.data.dto.user.ReservationAddDto;
import com.goo99.goosreservation.data.entity.Owner;
import com.goo99.goosreservation.data.entity.Reservation;
import com.goo99.goosreservation.data.entity.Studio;
import com.goo99.goosreservation.exception.CustomException;
import com.goo99.goosreservation.repository.OwnerRepo;
import com.goo99.goosreservation.repository.ReservationRepo;
import com.goo99.goosreservation.repository.StudioRepo;
import com.goo99.goosreservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.goo99.goosreservation.type.ErrorCode.OWNER_NOTFOUND;
import static com.goo99.goosreservation.type.ErrorCode.STUDIO_NOTFOUND;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

  private final ReservationRepo reservationRepo;
  private final StudioRepo studioRepo;
  private final OwnerRepo ownerRepo;


  @Override
  public Queue<Integer> getAvailableTimeList(ReservationAddDto addDto) {
    List<Integer> available = new ArrayList<>();
    Studio studio = findStudioById(addDto.getStudioId());

    LocalDateTime request =
      LocalDateTime.of(addDto.getYear(), addDto.getMonth(), addDto.getDate(), 0, 0);

    // 요청된 날짜에서 확정된 예약의 시간은 제외한다.
    List<DateMapping> acceptedList =
      reservationRepo.findAllByReservedAtBetweenAndIsAcceptedAndStudioId(
        request, request.plusDays(1).minusMinutes(1), 1, addDto.getStudioId());

    // 총 영업시간에서 확정예약의 시간 제외처리
    for (int i = studio.getOpen(); i <= studio.getClose(); i++) {
      available.add(i);
    }
    for (int i = 0; i < acceptedList.size(); i++) {
      int idx = available.indexOf(acceptedList.get(i).getReservedAt().getHour());

      for (int j = 0; j < acceptedList.get(i).getTime(); j++) {
        available.remove(idx);
      }
    }
    // 현재 시간 기준 10분 미만으로 남은 예약은 선택 못하도록 처리
    LocalDateTime now = LocalDateTime.now();
    if (now.getYear() == addDto.getYear() && now.getMonthValue() == addDto.getMonth()
      && now.getDayOfMonth() == addDto.getDate()) {

      // from 시 부터 예약가능
      int from = now.plusMinutes(10).getHour() + 1;

      for (int i = 0; i < from; i++) {
        available.remove((Integer) i);
      }
    }

    return new LinkedList<>(available);
  }

  /**
   * 해당 매장의 오픈시간, 마감시간을 초기화한다.
   */
  @Override
  public void setOpeningHours(ReservationAddDto addDto) {
    Studio studio = findStudioById(addDto.getStudioId());

    addDto.setOpen(studio.getOpen());
    addDto.setClose(studio.getClose());
  }

  @Override
  public ReservationInfoDto save(ReservationAddDto addDto) {
    Studio studio = findStudioById(addDto.getStudioId());

    // 예약 10분 전 마감 더블체크
    LocalDateTime now = LocalDateTime.now();
    if (now.getYear() == addDto.getYear() && now.getMonthValue() == addDto.getMonth()
      && now.getDayOfMonth() == addDto.getDate()) {

      int curHour = LocalDateTime.now().plusMinutes(10).getHour();

      List<Integer> times = addDto.getTimes();
      for (int i = 0; i < times.size(); i++) {
        if (times.get(i) <= curHour) {
          throw new RuntimeException("예약할 수 없는 날짜입니다.");
        }
      }
    }

    Reservation reservation =
      reservationRepo.save(ReservationAddDto.toEntity(addDto));
    Owner owner = findOwnerById(studio.getOwnerId());

    return ReservationInfoDto.from(reservation, studio, owner);
  }

  public Studio findStudioById(Long studioId) {
    return studioRepo.findById(studioId)
      .orElseThrow(() -> new CustomException(STUDIO_NOTFOUND));
  }

  public Owner findOwnerById(Long ownerId) {
    return ownerRepo.findById(ownerId)
      .orElseThrow(() -> new CustomException(OWNER_NOTFOUND));
  }
}
