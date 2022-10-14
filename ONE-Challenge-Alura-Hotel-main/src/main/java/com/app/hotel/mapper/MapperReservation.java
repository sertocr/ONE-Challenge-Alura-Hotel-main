package com.app.hotel.mapper;

import com.app.hotel.model.entity.Guest;
import com.app.hotel.model.entity.Reservation;
import com.app.hotel.model.request.ReservationRequest;
import com.app.hotel.model.response.ReservationResponse;
import com.app.hotel.model.response.ListReservationResponse;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperReservation {

  private final ModelMapper modelMapper;

  @Autowired
  public MapperReservation(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public Reservation map(ReservationRequest request) {
    return modelMapper.map(request, Reservation.class);
  }

  public ReservationResponse map(Reservation reservation) {
    return modelMapper.map(reservation, ReservationResponse.class);
  }

  public ListReservationResponse map(List<Reservation> reservations, Guest guest) {
    ListReservationResponse listReservationResponse = new ListReservationResponse();
    List<ReservationResponse> reservationResponses = new ArrayList<>();
    reservations.forEach(e -> {
      reservationResponses.add(map(e));
    });
    listReservationResponse.setReservations(reservationResponses);
    return listReservationResponse;
  }
}
