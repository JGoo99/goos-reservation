package com.goo99.goosreservation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  USER_NOTFOUND("해당 유저를 찾을 수 없습니다."),
  DUPLICATE_USER_EMAIL("이미 회원가입된 이메일입니다.");

  private final String description;
}
