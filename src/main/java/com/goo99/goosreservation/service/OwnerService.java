package com.goo99.goosreservation.service;

import com.goo99.goosreservation.data.dto.owner.OwnerJoinDto;

public interface OwnerService {

  boolean join(OwnerJoinDto ownerJoinDto);

  boolean isStudioRegistered(Long driverId);
}
