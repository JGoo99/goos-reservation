package com.goo99.goosreservation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  NO_AUTHORITY("접근 권한이 없습니다."),

  // 유저
  USER_NOTFOUND("해당 유저데이터를 찾을 수 없습니다."),
  DUPLICATE_USER_EMAIL("이미 회원가입된 유저 이메일입니다."),

  // 오너
  OWNER_NOTFOUND("해당 기사데이터를 찾을 수 없습니다. "),
  DUPLICATE_OWNER_EMAIL("이미 회원가입된 기사 이메일입니다."),

  // 시설
  STUDIO_NOTFOUND("해당 택시데이터를 찾을 수 없습니다. "),

  // 예약
  RESERVATION_NOTFOUND("해당 예약데이터를 찾을 수 없습니다.");

  private final String description;
}
