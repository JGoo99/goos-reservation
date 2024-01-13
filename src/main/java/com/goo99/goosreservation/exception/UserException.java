package com.goo99.goosreservation.exception;

import com.goo99.goosreservation.type.ErrorCode;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserException extends RuntimeException {

  private ErrorCode errorCode;
  private String errorMessage;

  public UserException(ErrorCode errorCode) {
    this.errorCode = errorCode;
    this.errorMessage = errorCode.getDescription();
  }

}
