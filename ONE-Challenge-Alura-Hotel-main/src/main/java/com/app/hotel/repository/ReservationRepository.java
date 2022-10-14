package com.app.hotel.repository;

import com.app.hotel.model.entity.Guest;
import com.app.hotel.model.entity.Reservation;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  boolean existsReservationByReservationFromDateAndReservationToDate(LocalDate from, LocalDate to);

  List<Reservation> findAllByGuest(Guest guest);

  Optional<Reservation> getReservationByReservationId(Long id);


}
