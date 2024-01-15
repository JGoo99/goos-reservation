package com.goo99.goosreservation.data.dto.owner;

import com.goo99.goosreservation.data.entity.Studio;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class StudioEditDto {

  @NotNull
  private String StudioName;

  @Size(min = 1, max = 20)
  private String oneLineExplain;

  @Min(0) @Max(24)
  private Integer open;

  @Min(0) @Max(24)
  private Integer close;

  @NotBlank
  private Long ownerId;

  @NotBlank
  private Long studioId;

  @NotBlank
  private String address1;

  private String address2;

  public static Studio toEntity(StudioEditDto studioEditDto) {
    return Studio.builder()
      .id(studioEditDto.getStudioId())
      .studioName(studioEditDto.getStudioName())
      .oneLineExplain(studioEditDto.getOneLineExplain())
      .open(studioEditDto.getOpen())
      .close(studioEditDto.getClose())
      .ownerId(studioEditDto.getOwnerId())
      .address1(studioEditDto.getAddress1())
      .address2(studioEditDto.getAddress2())
      .build();
  }
}
