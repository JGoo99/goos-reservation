package com.goo99.goosreservation.repository;

import com.goo99.goosreservation.data.entity.Studio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepo extends JpaRepository<Studio, Long> {

  Page<Studio> findAllBy(Pageable pageable);

  Page<Studio> findAllByOwnerId(Long ownerId, Pageable pageable);

  boolean existsByOwnerId(Long ownerId);

  boolean existsByIdAndOwnerId(Long studioId, Long ownerId);

}
