package com.goo99.goosreservation.service.impl;

import com.goo99.goosreservation.data.dto.UserCustomDetails;
import com.goo99.goosreservation.data.entity.User;
import com.goo99.goosreservation.exception.UserException;
import com.goo99.goosreservation.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.goo99.goosreservation.type.ErrorCode.USER_NOTFOUND;

@Service
public class UserCustomDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Optional<User> user = userRepo.findByEmail(email);

    if (user.isPresent()) {
      return new UserCustomDetails(user.get());
    } else {
      throw new UserException(USER_NOTFOUND);
    }

  }
}
