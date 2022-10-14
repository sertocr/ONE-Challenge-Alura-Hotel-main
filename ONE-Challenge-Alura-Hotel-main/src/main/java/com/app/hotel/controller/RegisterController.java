package com.app.hotel.controller;

import com.app.hotel.exception.UserAlreadyExistException;
import com.app.hotel.model.request.RegisterRequest;
import com.app.hotel.model.response.RegisterResponse;
import com.app.hotel.service.RegisterImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RegisterController {

  private final RegisterImpl register;

  @Autowired
  public RegisterController(RegisterImpl register) {
    this.register = register;
  }

  @PostMapping("/register")
  public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request)
      throws UserAlreadyExistException {

    RegisterResponse response = register.register(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
