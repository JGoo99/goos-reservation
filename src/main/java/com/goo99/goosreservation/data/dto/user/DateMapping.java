package com.goo99.goosreservation.data.dto.user;

import java.time.LocalDateTime;

public interface DateMapping {
  LocalDateTime getReservedAt();

  int getTime();
}
