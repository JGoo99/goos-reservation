package com.goo99.goosreservation.data.dto.owner;

import com.goo99.goosreservation.data.entity.Owner;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.goo99.goosreservation.type.RoleType.ROLE_OWNER;

@Getter
@Setter
@Builder
@ToString
public class OwnerJoinDto {

  @NotBlank
  @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
  private String email;

  @NotBlank
  private String ownerName;

  @NotBlank
  @Size(min = 3)
  private String password;

  @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
  private String phone;

  @NotNull
  private String address;

  public static Owner toEntity(OwnerJoinDto ownerJoinDto) {
    return Owner.builder()
      .email(ownerJoinDto.getEmail())
      .ownerName(ownerJoinDto.getOwnerName())
      .password(ownerJoinDto.getPassword())
      .phone(ownerJoinDto.getPhone())
      .address(ownerJoinDto.getAddress())
      .role(ROLE_OWNER.toString())
      .build();
  }

}