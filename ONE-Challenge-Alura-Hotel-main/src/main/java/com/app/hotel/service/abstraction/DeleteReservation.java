package com.app.hotel.service.abstraction;

import com.app.hotel.exception.ReservationNotFoundException;

public interface DeleteReservation {

  public void delete(Long id) throws ReservationNotFoundException;
}
