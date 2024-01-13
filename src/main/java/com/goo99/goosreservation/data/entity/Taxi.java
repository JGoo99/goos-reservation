package com.goo99.goosreservation.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "taxi")
@ToString(callSuper = true)
public class Taxi extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String CarType;

  private String oneLineExplain;

  private int stars; // 총 별점
  private int reviewCount;

  private int open;
  private int close;

  private Long driverId;
}
