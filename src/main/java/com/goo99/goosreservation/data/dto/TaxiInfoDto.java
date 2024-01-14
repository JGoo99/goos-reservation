package com.goo99.goosreservation.data.dto;

import com.goo99.goosreservation.data.entity.Taxi;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class TaxiInfoDto {

  private String carType;
  private String oneLineExplain;

  private Integer open;
  private Integer close;

  private int stars;
  private int reviewCount;

  private String driverName;

  public static TaxiInfoDto from(Taxi taxi, String driverName) {
    return TaxiInfoDto.builder()
      .carType(taxi.getCarType())
      .oneLineExplain(taxi.getOneLineExplain())
      .open(taxi.getOpen())
      .close(taxi.getClose())
      .stars(taxi.getStars())
      .reviewCount(taxi.getReviewCount())
      .driverName(driverName)
      .build();
  }
}
