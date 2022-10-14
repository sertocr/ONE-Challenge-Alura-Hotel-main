package com.app.hotel.service.abstraction;

import com.app.hotel.exception.ReservationNotFoundException;
import com.app.hotel.model.request.ReservationRequest;
import com.app.hotel.model.response.ReservationResponse;

public interface UpdateReservation {

  public ReservationResponse update(Long id, ReservationRequest request) throws ReservationNotFoundException;
}
