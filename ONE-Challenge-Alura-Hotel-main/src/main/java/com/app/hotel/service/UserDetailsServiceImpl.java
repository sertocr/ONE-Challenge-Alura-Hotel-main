package com.app.hotel.service;

import com.app.hotel.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final GuestRepository guestRepository;

  @Autowired
  public UserDetailsServiceImpl(GuestRepository guestRepository) {
    this.guestRepository = guestRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return guestRepository.findGuestByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("Guest not found"));
  }
}
