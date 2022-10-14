package com.app.hotel.config.security;

import com.app.hotel.common.JwtUtils;
import com.app.hotel.service.UserDetailsServiceImpl;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

  private final JwtUtils jwtUtils;

  private final UserDetailsServiceImpl detailsService;

  @Autowired
  public JwtAuthenticationTokenFilter(JwtUtils jwtUtils, UserDetailsServiceImpl detailsService) {
    this.jwtUtils = jwtUtils;
    this.detailsService = detailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    System.out.println("Request URL: " + request.getRequestURL());
    String token = jwtUtils.getToken(request);

    if (!Objects.isNull(token)) {
      try {
        String username = jwtUtils.getUsernameFromToken(token);
        if (!Objects.isNull(username) && jwtUtils.isValidToken(token)) {
          UserDetails userDetails = detailsService.loadUserByUsername(username);
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
              userDetails.getUsername(), null, userDetails.getAuthorities());

          SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
          throw new Exception("INVALID USERNAME");
        }
      } catch (Exception e) {
        e.printStackTrace();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
      }
    }

    filterChain.doFilter(request, response);
  }
}
