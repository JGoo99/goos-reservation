package com.goo99.goosreservation.service.impl.driver;

import com.goo99.goosreservation.data.dto.DriverJoinDto;
import com.goo99.goosreservation.exception.CustomException;
import com.goo99.goosreservation.repository.DriverRepo;
import com.goo99.goosreservation.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.goo99.goosreservation.type.ErrorCode.DRIVER_NOTFOUND;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

  private final DriverRepo driverRepo;
  private final PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

  @Override
  public boolean join(DriverJoinDto driverJoinDto) {

    validateDuplicationEmail(driverJoinDto.getEmail());
    setEncodedPassword(driverJoinDto);

    driverRepo.save(DriverJoinDto.toEntity(driverJoinDto));

    return true;
  }

  public void validateDuplicationEmail(String email) {
    if (driverRepo.existsByEmail(email)) {
      throw new CustomException(DRIVER_NOTFOUND);
    }
  }

  public void setEncodedPassword(DriverJoinDto driverJoinDto) {
    driverJoinDto.setPassword(bCryptPasswordEncoder.encode(driverJoinDto.getPassword()));
  }
}
