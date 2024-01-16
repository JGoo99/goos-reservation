package com.goo99.goosreservation.data.dto;

import com.goo99.goosreservation.data.entity.Owner;
import com.goo99.goosreservation.data.entity.Reservation;
import com.goo99.goosreservation.data.entity.Studio;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.format.TextStyle;
import java.util.Locale;

@Getter
@Setter
@Builder
@ToString
public class ReservationInfoDto {

  private Long id;

  private String studioName;
  private String address1;
  private String address2;

  private String userName;
  private String userPhone;

  private Integer year;
  private Integer month;
  private Integer day;
  private String week;
  private Integer hour;
  private Integer time;

  private String isAccepted;
  private String isVisited;

  private String ownerName;
  private String ownerPhone;


  public static ReservationInfoDto from(Reservation reservation, Studio studio, Owner owner) {
    String isAccepted = "";
    switch (reservation.getIsAccepted()) {
      case -1:
        isAccepted = "거부";
        break;
      case 1:
        isAccepted = "승인";
        break;
      default:
        isAccepted = "ERROR(예약 오류)";
    }

    String isVisited = "";
    if (reservation.isVisited()) {
      isVisited = "방문 완료";
    } else {
      isVisited = "방문 기록이 없음";
    }

    return ReservationInfoDto.builder()
      .id(reservation.getId())
      .studioName(studio.getStudioName())
      .userName(reservation.getUserName())
      .userPhone(reservation.getUserPhone())
      .year(reservation.getReservedAt().getYear())
      .month(reservation.getReservedAt().getMonthValue())
      .day(reservation.getReservedAt().getDayOfMonth())
      .week(reservation.getReservedAt().getDayOfWeek()
        .getDisplayName(TextStyle.NARROW, Locale.KOREA))
      .hour(reservation.getReservedAt().getHour())
      .time(reservation.getTime())
      .isVisited(isVisited)
      .isAccepted(isAccepted)
      .ownerName(owner.getOwnerName())
      .ownerPhone(owner.getPhone())
      .address1(studio.getAddress1())
      .address2(studio.getAddress2())
      .build();
  }

}
