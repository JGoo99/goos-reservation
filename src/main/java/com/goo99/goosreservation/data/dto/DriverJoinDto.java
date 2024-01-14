package com.goo99.goosreservation.data.dto;

import com.goo99.goosreservation.data.entity.Driver;
import com.goo99.goosreservation.data.entity.User;
import com.goo99.goosreservation.type.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.goo99.goosreservation.type.RoleType.ROLE_DRIVER;

@Getter
@Setter
@Builder
@ToString
public class DriverJoinDto {

  @NotBlank
  @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
  private String email;

  @NotBlank
  private String driverName;

  @NotBlank
  @Size(min = 3)
  private String password;

  @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
  private String phone;

  @NotNull
  private String address;

  public static Driver toEntity(DriverJoinDto driverJoinDto) {
    return Driver.builder()
      .email(driverJoinDto.getEmail())
      .driverName(driverJoinDto.getDriverName())
      .password(driverJoinDto.getPassword())
      .phone(driverJoinDto.getPhone())
      .address(driverJoinDto.getAddress())
      .role(ROLE_DRIVER.toString())
      .build();
  }

}