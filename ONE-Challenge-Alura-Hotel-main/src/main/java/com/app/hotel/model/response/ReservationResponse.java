package com.app.hotel.model.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationResponse {

  private LocalDate reservationFromDate;

  private LocalDate reservationToDate;

  private Double pay;

  private String methodPay;

  private GetGuestResponse guest;
}
