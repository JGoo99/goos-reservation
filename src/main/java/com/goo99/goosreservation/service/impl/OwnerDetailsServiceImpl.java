package com.goo99.goosreservation.service.impl;

import com.goo99.goosreservation.data.dto.owner.OwnerDetails;
import com.goo99.goosreservation.data.entity.Owner;
import com.goo99.goosreservation.exception.CustomException;
import com.goo99.goosreservation.repository.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.goo99.goosreservation.type.ErrorCode.OWNER_NOTFOUND;

@Service
public class OwnerDetailsServiceImpl implements UserDetailsService {

  @Autowired
  OwnerRepo ownerRepo;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Optional<Owner> owner = ownerRepo.findByEmail(email);

    if (owner.isPresent()) {
      return new OwnerDetails(owner.get());
    } else {
      throw new CustomException(OWNER_NOTFOUND);
    }

  }
}
