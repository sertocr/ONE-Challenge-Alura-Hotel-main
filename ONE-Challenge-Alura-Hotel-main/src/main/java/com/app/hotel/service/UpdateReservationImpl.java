package com.app.hotel.service;

import com.app.hotel.exception.ReservationNotFoundException;
import com.app.hotel.mapper.MapperReservation;
import com.app.hotel.model.entity.Reservation;
import com.app.hotel.model.request.ReservationRequest;
import com.app.hotel.model.response.ReservationResponse;
import com.app.hotel.repository.ReservationRepository;
import com.app.hotel.service.abstraction.UpdateReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateReservationImpl implements UpdateReservation {

  private final ReservationRepository reservationRepository;

  private final MapperReservation mapperReservation;

  @Autowired
  public UpdateReservationImpl(ReservationRepository reservationRepository,
      MapperReservation mapperReservation) {
    this.reservationRepository = reservationRepository;
    this.mapperReservation = mapperReservation;
  }


  @Override
  public ReservationResponse update(Long id, ReservationRequest request) throws ReservationNotFoundException {

    Reservation reservation = reservationRepository.getReservationByReservationId(id)
        .orElseThrow(() -> new ReservationNotFoundException("Reservation Not Found"));

    updateData(reservation, request);

    return mapperReservation.map(reservationRepository.save(reservation));
  }

  private void updateData(Reservation reservation, ReservationRequest request) {
    reservation.setReservationFromDate(request.getReservationFromDate());
    reservation.setReservationToDate(request.getReservationToDate());
    reservation.setPay(request.getPay());
  }
}
