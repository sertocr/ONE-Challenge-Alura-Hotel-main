package com.app.hotel.exception;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse {

  private HttpStatus httpStatus;

  private List<String> messages;

  private Timestamp timestamp;

  public ErrorResponse() {
    this.messages = new ArrayList<>();
  }

  public void add(String message) {
    this.messages.add(message);
  }
}
