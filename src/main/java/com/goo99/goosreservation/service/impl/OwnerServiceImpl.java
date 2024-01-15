package com.goo99.goosreservation.service.impl;

import com.goo99.goosreservation.data.dto.owner.OwnerJoinDto;
import com.goo99.goosreservation.data.entity.Owner;
import com.goo99.goosreservation.exception.CustomException;
import com.goo99.goosreservation.repository.OwnerRepo;
import com.goo99.goosreservation.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.goo99.goosreservation.type.ErrorCode.OWNER_NOTFOUND;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

  private final OwnerRepo ownerRepo;
  private final PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

  @Override
  public boolean join(OwnerJoinDto ownerJoinDto) {

    validateDuplicationEmail(ownerJoinDto.getEmail());
    setEncodedPassword(ownerJoinDto);

    ownerRepo.save(OwnerJoinDto.toEntity(ownerJoinDto));

    return true;
  }

  @Override
  public boolean isStudioRegistered(Long ownerId) {

    Owner owner = findOwnerById(ownerId);

    return (owner.getStudioId() != null);
  }

  public void validateDuplicationEmail(String email) {
    if (ownerRepo.existsByEmail(email)) {
      throw new CustomException(OWNER_NOTFOUND);
    }
  }

  public void setEncodedPassword(OwnerJoinDto ownerJoinDto) {
    ownerJoinDto.setPassword(bCryptPasswordEncoder.encode(ownerJoinDto.getPassword()));
  }

  public Owner findOwnerById(Long ownerId) {
    return ownerRepo.findById(ownerId)
      .orElseThrow(() -> new CustomException(OWNER_NOTFOUND));
  }
}
