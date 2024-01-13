package com.goo99.goosreservation.repository;

import com.goo99.goosreservation.data.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<Driver, Long> {
}
