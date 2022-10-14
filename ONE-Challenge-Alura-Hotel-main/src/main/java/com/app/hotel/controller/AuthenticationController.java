package com.app.hotel.controller;

import com.app.hotel.exception.InvalidCredentialsException;
import com.app.hotel.model.request.AuthenticationRequest;
import com.app.hotel.model.response.AuthenticationResponse;
import com.app.hotel.service.LoginImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final LoginImpl login;

  @Autowired
  public AuthenticationController(LoginImpl login) {
    this.login = login;
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(
      @Valid @RequestBody AuthenticationRequest request) throws InvalidCredentialsException {
    return ResponseEntity.ok(login.login(request));
  }
}
