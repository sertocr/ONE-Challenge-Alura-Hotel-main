package com.app.hotel.service.abstraction;

import com.app.hotel.exception.ReservationAlreadyDoneException;
import com.app.hotel.model.request.ReservationRequest;
import com.app.hotel.model.response.ReservationResponse;

public interface CreateReservation {

  public ReservationResponse create(ReservationRequest request)
      throws ReservationAlreadyDoneException;
}
