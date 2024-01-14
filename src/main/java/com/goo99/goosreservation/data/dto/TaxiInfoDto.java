package com.goo99.goosreservation.data.dto;

import com.goo99.goosreservation.data.entity.Driver;
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

  private Long taxiId;

  private String carType;
  private String oneLineExplain;

  private Integer open;
  private Integer close;

  private int stars;
  private int reviewCount;

  private String driverName;
  private String driverPhone;

  public static TaxiInfoDto from(Taxi taxi, Driver driver) {
    return TaxiInfoDto.builder()
      .taxiId(taxi.getId())
      .carType(taxi.getCarType())
      .oneLineExplain(taxi.getOneLineExplain())
      .open(taxi.getOpen())
      .close(taxi.getClose())
      .stars(taxi.getStars())
      .reviewCount(taxi.getReviewCount())
      .driverName(driver.getDriverName())
      .driverPhone(driver.getPhone())
      .build();
  }
}
