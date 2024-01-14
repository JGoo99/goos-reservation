package com.goo99.goosreservation.service;

import com.goo99.goosreservation.data.dto.UserJoinDto;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Map;

public interface UserService {

  boolean join(UserJoinDto userJoinDto);

  Map<String, String> getValidExceptionResult(List<ObjectError> allErrors);
}
