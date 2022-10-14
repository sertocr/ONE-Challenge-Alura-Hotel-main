package com.app.hotel.exception;

public class UserAlreadyExistException extends Exception{

  public UserAlreadyExistException(String message) {
    super(message);
  }
}
