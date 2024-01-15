package com.goo99.goosreservation.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "owner")
@ToString(callSuper = true)
public class Owner extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String email;

  private String ownerName;
  private String password;
  private String phone;
  private String address;
  private String role;

  private Long studioId;
}
