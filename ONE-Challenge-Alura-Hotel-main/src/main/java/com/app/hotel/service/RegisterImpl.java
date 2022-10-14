package com.app.hotel.service;

import com.app.hotel.common.JwtUtils;
import com.app.hotel.config.security.RoleType;
import com.app.hotel.exception.UserAlreadyExistException;
import com.app.hotel.mapper.GuestMapper;
import com.app.hotel.model.entity.Guest;
import com.app.hotel.model.request.RegisterRequest;
import com.app.hotel.model.response.RegisterResponse;
import com.app.hotel.repository.GuestRepository;
import com.app.hotel.repository.RoleRepository;
import com.app.hotel.service.abstraction.Register;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterImpl implements Register {

  private final GuestRepository guestRepository;
  private final RoleRepository roleRepository;
  private final GuestMapper guestMapper;
  private final BCryptPasswordEncoder encoder;

  private final JwtUtils jwtUtils;

  @Autowired
  public RegisterImpl(GuestRepository guestRepository, RoleRepository roleRepository,
      GuestMapper guestMapper, BCryptPasswordEncoder encoder, JwtUtils jwtUtils) {
    this.guestRepository = guestRepository;
    this.roleRepository = roleRepository;
    this.guestMapper = guestMapper;
    this.encoder = encoder;
    this.jwtUtils = jwtUtils;
  }

  @Override
  public RegisterResponse register(RegisterRequest request) throws UserAlreadyExistException {

    if(guestRepository.existsGuestByEmail(request.getEmail())) {
      throw new UserAlreadyExistException("User already exist");
    }

    Guest guest = guestMapper.map(request);
    guest.setPassword(encoder.encode(guest.getPassword()));
    guest.setRoles(List.of(roleRepository.findRoleByName(RoleType.USER.getFullRoleName())));

    RegisterResponse response = guestMapper.map(guestRepository.save(guest));
    response.setToken(jwtUtils.generateToken(guest.getUsername(),
        (Collection<GrantedAuthority>) guest.getAuthorities()));

    return response;
  }
}
