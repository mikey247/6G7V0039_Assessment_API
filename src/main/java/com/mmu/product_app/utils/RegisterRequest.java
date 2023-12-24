package com.mmu.product_app.utils;


import com.mmu.product_app.models.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Role role;
}
