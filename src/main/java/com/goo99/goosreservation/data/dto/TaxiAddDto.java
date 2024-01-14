package com.goo99.goosreservation.data.dto;

import com.goo99.goosreservation.data.entity.Taxi;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class TaxiAddDto {

  @NotNull
  private String CarType;

  @Size(min = 1, max = 20)
  private String oneLineExplain;

  @Min(0) @Max(24)
  private Integer open;

  @Min(0) @Max(24)
  private Integer close;

  @NotBlank
  private Long driverId;

  public static Taxi toEntity(TaxiAddDto taxiAddDto) {
    return Taxi.builder()
      .carType(taxiAddDto.CarType)
      .oneLineExplain(taxiAddDto.getOneLineExplain())
      .stars(0)
      .reviewCount(0)
      .open(taxiAddDto.getOpen())
      .close(taxiAddDto.getClose())
      .driverId(taxiAddDto.getDriverId())
      .build();
  }
}
