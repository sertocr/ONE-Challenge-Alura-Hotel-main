package com.app.hotel.mapper;

import com.app.hotel.model.entity.Guest;
import com.app.hotel.model.request.RegisterRequest;
import com.app.hotel.model.response.RegisterResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuestMapper {

  private final ModelMapper modelMapper;

  @Autowired
  public GuestMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public Guest map(RegisterRequest request) {
    return modelMapper.map(request, Guest.class);
  }

  public RegisterResponse map(Guest guest) {
    return modelMapper.map(guest, RegisterResponse.class);
  }
}
