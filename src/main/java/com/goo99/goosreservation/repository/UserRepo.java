package com.goo99.goosreservation.repository;

import com.goo99.goosreservation.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
