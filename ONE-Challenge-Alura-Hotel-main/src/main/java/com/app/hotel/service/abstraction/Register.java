package com.app.hotel.service.abstraction;

import com.app.hotel.exception.UserAlreadyExistException;
import com.app.hotel.model.request.RegisterRequest;
import com.app.hotel.model.response.RegisterResponse;

public interface Register {

  public RegisterResponse register(RegisterRequest request) throws UserAlreadyExistException;
}
