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
public class StudioAddDto {

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

  public static Studio toEntity(StudioAddDto studioAddDto) {
    return Studio.builder()
      .studioName(studioAddDto.getStudioName())
      .oneLineExplain(studioAddDto.getOneLineExplain())
      .stars(0)
      .reviewCount(0)
      .open(studioAddDto.getOpen())
      .close(studioAddDto.getClose())
      .ownerId(studioAddDto.getOwnerId())
      .build();
  }
}
