package com.app.hotel.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListReservationResponse {

  @JsonProperty("reservations")
  private List<ReservationResponse> reservations;
}
