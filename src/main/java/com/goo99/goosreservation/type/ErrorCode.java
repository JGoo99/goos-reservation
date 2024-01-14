package com.goo99.goosreservation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  // 유저
  USER_NOTFOUND("해당 유저데이터를 찾을 수 없습니다."),
  DUPLICATE_USER_EMAIL("이미 회원가입된 유저 이메일입니다."),

  // 기사
  DRIVER_NOTFOUND("해당 기사데이터를 찾을 수 없습니다. "),
  DUPLICATE_DRIVER_EMAIL("이미 회원가입된 기사 이메일입니다.");

  private final String description;
}
