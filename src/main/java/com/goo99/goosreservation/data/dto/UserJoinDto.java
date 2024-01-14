package com.goo99.goosreservation.data.dto;

import com.goo99.goosreservation.data.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.goo99.goosreservation.type.RoleType.ROLE_USER;

@Getter
@Setter
@Builder
@ToString
public class UserJoinDto {

  @NotBlank
  @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
  private String email;

  @NotBlank
  private String username;

  @NotNull
  @Size(min = 2, max = 10)
  private String nickname;

  @NotBlank
  @Size(min = 3)
  private String password;

  @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
  private String phone;

  @NotNull
  private String address;

  @NotNull
  private String impairmentType;

  @NotNull
  private Boolean hasWheelchair;

  public static User toEntity(UserJoinDto userJoinDto) {
    return User.builder()
      .email(userJoinDto.getEmail())
      .username(userJoinDto.getUsername())
      .nickname(userJoinDto.getNickname())
      .password(userJoinDto.getPassword())
      .phone(userJoinDto.getPhone())
      .address(userJoinDto.getAddress())
      .impairmentType(userJoinDto.impairmentType)
      .hasWheelchair(userJoinDto.hasWheelchair)
      .role(ROLE_USER.toString())
      .build();
  }

}