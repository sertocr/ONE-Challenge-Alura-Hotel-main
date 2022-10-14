package com.app.hotel.exception;

import java.sql.Timestamp;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalHandleException {

  public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(
      UserAlreadyExistException exception) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST,
        exception.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
      InvalidCredentialsException exception) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST,
        exception.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  public ResponseEntity<ErrorResponse> handleReservationNotFoundException(
      ReservationNotFoundException exception) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST,
        exception.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  public ResponseEntity<ErrorResponse> handleReservationAlreadyDoneException(
      ReservationAlreadyDoneException exception) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST,
        exception.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  private ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message) {
    ErrorResponse error = new ErrorResponse();
    error.setHttpStatus(httpStatus);
    error.add(message);
    error.setTimestamp(Timestamp.from(Instant.now()));
    return error;
  }
}
