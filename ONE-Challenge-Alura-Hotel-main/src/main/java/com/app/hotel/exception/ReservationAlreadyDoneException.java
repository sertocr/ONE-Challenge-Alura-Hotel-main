package com.app.hotel.exception;

public class ReservationAlreadyDoneException extends Exception {

  public ReservationAlreadyDoneException(String message) {
    super(message);
  }
}
