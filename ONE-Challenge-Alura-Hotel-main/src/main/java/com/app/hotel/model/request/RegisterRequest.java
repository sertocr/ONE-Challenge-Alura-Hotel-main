package com.app.hotel.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

  @NotBlank(message = "Name cannot be empty or null.")
  private String name;
  @NotBlank(message = "surname cannot be empty or null.")
  private String surname;
  @Email
  @NotBlank(message = "Email cannot be empty or null.")
  private String email;
  @NotBlank(message = "Username cannot be empty or null.")
  private String password;
}
