package com.goo99.goosreservation.service;

import com.goo99.goosreservation.data.dto.DriverJoinDto;

public interface DriverService {

  boolean join(DriverJoinDto driverJoinDto);

  boolean isTaxiRegistered(Long driverId);
}
