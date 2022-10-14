package com.app.hotel.model.request;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequest {

  @NotNull(message = "Date is required")
  private LocalDate reservationFromDate;

  @NotNull(message = "Date is required")
  private LocalDate reservationToDate;

  @NotNull(message = "Pay is required")
  private Double pay;

  @NotBlank(message = "Method pay is required")
  private String methodPay;
}
