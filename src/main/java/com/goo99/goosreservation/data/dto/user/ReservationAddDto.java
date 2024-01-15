package com.goo99.goosreservation.data.dto.user;

import com.goo99.goosreservation.data.entity.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
public class ReservationAddDto {

  private int year;
  private int month;

  private Integer date;
  private Long studioId;
  private Long userId;
  private String userName;
  private String userPhone;
  private Integer open;
  private Integer close;

  private List<Integer> times;

  public static Reservation toEntity(ReservationAddDto reservationAddDto) {
    List<Integer> times = reservationAddDto.getTimes();
    int from = times.get(0);
    int time = times.size();

    return Reservation.builder()
      .isVisited(false)
      .isAccepted(0)
      .reservedAt(LocalDateTime.of(
        reservationAddDto.getYear(),
        reservationAddDto.getMonth(),
        reservationAddDto.getDate(),
        from, 0))
      .time(time)
      .studioId(reservationAddDto.getStudioId())
      .userName(reservationAddDto.userName)
      .userPhone(reservationAddDto.userPhone)
      .userId(reservationAddDto.getUserId())
      .build();
  }
}
