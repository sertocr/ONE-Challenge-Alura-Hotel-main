package com.app.hotel.service;

import com.app.hotel.exception.ReservationNotFoundException;
import com.app.hotel.model.entity.Reservation;
import com.app.hotel.repository.ReservationRepository;
import com.app.hotel.service.abstraction.DeleteReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteReservationImpl implements DeleteReservation {

  private final ReservationRepository reservationRepository;

  @Autowired
  public DeleteReservationImpl(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  @Override
  public void delete(Long id) throws ReservationNotFoundException {

    Reservation reservation = reservationRepository.getReservationByReservationId(id)
        .orElseThrow(() -> new ReservationNotFoundException("Reservation Not Found"));

    reservationRepository.delete(reservation);
  }
}
