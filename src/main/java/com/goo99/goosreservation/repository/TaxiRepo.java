package com.goo99.goosreservation.repository;

import com.goo99.goosreservation.data.entity.Taxi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxiRepo extends JpaRepository<Taxi, Long> {

  Page<Taxi> findAllBy(Pageable pageable);

}
