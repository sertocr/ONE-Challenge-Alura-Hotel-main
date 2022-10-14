package com.app.hotel.config.security;

import com.app.hotel.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
      BCryptPasswordEncoder encoder,
      UserDetailsServiceImpl userDetailsService) throws Exception {
    return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userDetailsService)
        .passwordEncoder(encoder)
        .and()
        .build();
  }
}
