package com.goo99.goosreservation.repository;

import com.goo99.goosreservation.data.dto.user.DateMapping;
import com.goo99.goosreservation.data.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {

  /**
   * 해당 날짜 확정 예약 조회 (해당 날짜의 0시0분 ~ 11시59분 조회)
   */
  List<DateMapping> findAllByReservedAtBetweenAndIsAcceptedAndStudioId(
    LocalDateTime from, LocalDateTime to, int isAccepted, Long studioId);

  Page<Reservation> findAllByOwnerIdAndIsAccepted(Long ownerId, int isAccepted, Pageable pageable);

  Optional<Reservation> findByIdAndOwnerId(Long reservationId, Long ownerId);

  Page<Reservation> findAllByUserId(Long userId, Pageable pageable);
}
