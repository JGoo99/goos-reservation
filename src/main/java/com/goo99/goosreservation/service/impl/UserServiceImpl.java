package com.goo99.goosreservation.service.impl;

import com.goo99.goosreservation.data.dto.user.UserJoinDto;
import com.goo99.goosreservation.exception.CustomException;
import com.goo99.goosreservation.repository.UserRepo;
import com.goo99.goosreservation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.goo99.goosreservation.type.ErrorCode.DUPLICATE_USER_EMAIL;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepo userRepo;
  private final PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

  @Override
  public boolean join(UserJoinDto userJoinDto) {

    validateDuplicationEmail(userJoinDto.getEmail());
    setEncodedPassword(userJoinDto);

    userRepo.save(UserJoinDto.toEntity(userJoinDto));

    return true;
  }

  @Override
  public Map<String, String> getValidExceptionResult(List<ObjectError> allErrors) {
    Map<String, String> errors = new HashMap<>();

    allErrors.forEach(m -> errors.put(((FieldError) m).getField(), m.getDefaultMessage()));
    return errors;
  }

  public void validateDuplicationEmail(String email) {
    if (userRepo.existsByEmail(email)) {
      throw new CustomException(DUPLICATE_USER_EMAIL);
    }
  }

  public void setEncodedPassword(UserJoinDto userJoinDto) {
    userJoinDto.setPassword(bCryptPasswordEncoder.encode(userJoinDto.getPassword()));
  }
}
