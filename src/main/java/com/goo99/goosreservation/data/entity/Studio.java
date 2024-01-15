package com.goo99.goosreservation.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "studio")
@ToString(callSuper = true)
public class Studio extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long ownerId;

  private String studioName;
  private String oneLineExplain;

  private String address1;
  private String address2;

  private int stars; // 총 별점
  private int reviewCount;

  private int open;
  private int close;
}
