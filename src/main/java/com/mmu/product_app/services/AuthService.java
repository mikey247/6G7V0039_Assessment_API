package com.mmu.product_app.services;

import com.mmu.product_app.models.User;
import com.mmu.product_app.repository.UserRepository;
import com.mmu.product_app.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .build();

    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(savedUser);
    return AuthenticationResponse.builder().accessToken(jwtToken).build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
   try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( request.getEmail(), request.getPassword() ));
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
   }catch (Exception e){
        System.out.println(e);
        return null;
   }
  } 
}
