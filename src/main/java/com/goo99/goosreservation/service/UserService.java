package com.goo99.goosreservation.service;

import com.goo99.goosreservation.data.dto.UserJoinDto;

public interface UserService {

  boolean join(UserJoinDto userJoinDto);
}
