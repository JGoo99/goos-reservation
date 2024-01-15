package com.goo99.goosreservation.data.dto;

import com.goo99.goosreservation.data.entity.Owner;
import com.goo99.goosreservation.data.entity.Studio;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class StudioInfoDto {

  private Long studioId;

  private String studioName;
  private String oneLineExplain;

  private Integer open;
  private Integer close;

  private int stars;
  private int reviewCount;

  private String ownerName;
  private String ownerPhone;

  public static StudioInfoDto from(Studio studio, Owner owner) {
    return StudioInfoDto.builder()
      .studioId(studio.getId())
      .studioName(studio.getStudioName())
      .oneLineExplain(studio.getOneLineExplain())
      .open(studio.getOpen())
      .close(studio.getClose())
      .stars(studio.getStars())
      .reviewCount(studio.getReviewCount())
      .ownerName(owner.getOwnerName())
      .ownerPhone(owner.getPhone())
      .build();
  }
}
