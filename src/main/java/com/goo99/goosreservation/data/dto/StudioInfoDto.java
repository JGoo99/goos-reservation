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

  private String ownerName;
  private String ownerPhone;

  private String address1;
  private String address2;

  public static StudioInfoDto from(Studio studio, Owner owner) {
    return StudioInfoDto.builder()
      .studioId(studio.getId())
      .studioName(studio.getStudioName())
      .oneLineExplain(studio.getOneLineExplain())
      .open(studio.getOpen())
      .close(studio.getClose())
      .ownerName(owner.getOwnerName())
      .ownerPhone(owner.getPhone())
      .address1(studio.getAddress1())
      .address2(studio.getAddress2())
      .build();
  }
}
