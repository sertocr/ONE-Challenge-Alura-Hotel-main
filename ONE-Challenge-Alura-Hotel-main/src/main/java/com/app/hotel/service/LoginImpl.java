package com.app.hotel.service;

import com.app.hotel.common.JwtUtils;
import com.app.hotel.exception.InvalidCredentialsException;
import com.app.hotel.model.entity.Guest;
import com.app.hotel.model.request.AuthenticationRequest;
import com.app.hotel.model.response.AuthenticationResponse;
import com.app.hotel.repository.GuestRepository;
import com.app.hotel.service.abstraction.Login;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginImpl implements Login {

  private final GuestRepository guestRepository;

  private final AuthenticationManager authenticationManager;

  private final JwtUtils jwtUtils;

  @Autowired
  public LoginImpl(GuestRepository guestRepository, AuthenticationManager authenticationManager,
      JwtUtils jwtUtils) {
    this.guestRepository = guestRepository;
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
  }

  @Override
  public AuthenticationResponse login(AuthenticationRequest request)
      throws InvalidCredentialsException {

    Guest guest = guestRepository.findGuestByEmail(request.getEmail())
        .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

    SecurityContextHolder.getContext().setAuthentication(
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())));

    return new AuthenticationResponse(guest.getUsername(), jwtUtils.generateToken(
        guest.getUsername(), (Collection<GrantedAuthority>) guest.getAuthorities()));
  }
}
