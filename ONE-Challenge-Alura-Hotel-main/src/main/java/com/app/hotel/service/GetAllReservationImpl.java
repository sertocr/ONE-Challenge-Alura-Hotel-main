package com.app.hotel.service;

import com.app.hotel.common.GetActualUserUtil;
import com.app.hotel.mapper.MapperReservation;
import com.app.hotel.model.entity.Guest;
import com.app.hotel.model.entity.Reservation;
import com.app.hotel.model.response.ListReservationResponse;
import com.app.hotel.repository.GuestRepository;
import com.app.hotel.repository.ReservationRepository;
import com.app.hotel.service.abstraction.GetAllReservation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetAllReservationImpl implements GetAllReservation {

  private final ReservationRepository reservationRepository;

  private final GuestRepository guestRepository;

  private final MapperReservation mapperReservation;

  @Autowired
  public GetAllReservationImpl(ReservationRepository reservationRepository,
      GuestRepository guestRepository, MapperReservation mapperReservation) {
    this.reservationRepository = reservationRepository;
    this.guestRepository = guestRepository;
    this.mapperReservation = mapperReservation;
  }

  @Override
  public ListReservationResponse getAll() {

    String email = GetActualUserUtil.getActualUser();

    Guest guest = guestRepository.findGuestByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    List<Reservation> reservationList = reservationRepository.findAllByGuest(guest);

    return mapperReservation.map(reservationList, guest);
  }
}
