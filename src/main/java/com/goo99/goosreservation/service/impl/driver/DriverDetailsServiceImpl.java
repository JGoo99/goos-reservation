package com.goo99.goosreservation.service.impl.driver;

import com.goo99.goosreservation.data.dto.DriverDetails;
import com.goo99.goosreservation.data.entity.Driver;
import com.goo99.goosreservation.exception.CustomException;
import com.goo99.goosreservation.repository.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.goo99.goosreservation.type.ErrorCode.DRIVER_NOTFOUND;

@Service
public class DriverDetailsServiceImpl implements UserDetailsService {

  @Autowired
  DriverRepo driverRepo;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Optional<Driver> driver = driverRepo.findByEmail(email);

    if (driver.isPresent()) {
      return new DriverDetails(driver.get());
    } else {
      throw new CustomException(DRIVER_NOTFOUND);
    }

  }
}
