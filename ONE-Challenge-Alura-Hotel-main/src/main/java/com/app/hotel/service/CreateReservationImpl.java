package com.app.hotel.service;

import com.app.hotel.common.GetActualUserUtil;
import com.app.hotel.exception.ReservationAlreadyDoneException;
import com.app.hotel.mapper.MapperReservation;
import com.app.hotel.model.entity.Guest;
import com.app.hotel.model.entity.Reservation;
import com.app.hotel.model.request.ReservationRequest;
import com.app.hotel.model.response.ReservationResponse;
import com.app.hotel.repository.GuestRepository;
import com.app.hotel.repository.ReservationRepository;
import com.app.hotel.service.abstraction.CreateReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CreateReservationImpl implements CreateReservation {

  private final ReservationRepository reservationRepository;

  private final GuestRepository guestRepository;

  private final MapperReservation mapperReservation;

  @Autowired
  public CreateReservationImpl(ReservationRepository reservationRepository,
      GuestRepository guestRepository, MapperReservation mapperReservation) {
    this.reservationRepository = reservationRepository;
    this.guestRepository = guestRepository;
    this.mapperReservation = mapperReservation;
  }

  @Override
  public ReservationResponse create(ReservationRequest request)
      throws ReservationAlreadyDoneException {

    if (reservationRepository.
        existsReservationByReservationFromDateAndReservationToDate(request.getReservationFromDate(),
            request.getReservationToDate())) {
      throw new ReservationAlreadyDoneException("Reservation already done");
    }

    String email = GetActualUserUtil.getActualUser();

    Guest guest = guestRepository.findGuestByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    Reservation reservation = mapperReservation.map(request);
    reservation.setGuest(guest);

    return mapperReservation.map(reservationRepository.save(reservation));
  }
}
