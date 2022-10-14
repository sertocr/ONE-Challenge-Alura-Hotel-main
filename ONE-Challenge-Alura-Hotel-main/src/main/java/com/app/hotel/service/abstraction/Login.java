package com.app.hotel.service.abstraction;

import com.app.hotel.exception.InvalidCredentialsException;
import com.app.hotel.model.request.AuthenticationRequest;
import com.app.hotel.model.response.AuthenticationResponse;

public interface Login {

  public AuthenticationResponse login(AuthenticationRequest request)
      throws InvalidCredentialsException;
}
