package com.mmu.product_app.services;

import com.mmu.product_app.models.User;
import com.mmu.product_app.repository.UserRepository;
import com.mmu.product_app.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The AuthService class provides authentication and registration services for users.
 * It handles user registration and authentication using Spring Security and JWT (JSON Web Token).
 */
@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  /**
   * Registers a new user with the provided registration request.
   *
   * @param request The registration request containing user details.
   * @return An AuthenticationResponse object containing the generated JWT access token.
   */
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

  /**
   * Authenticates a user with the provided authentication request.
   *
   * @param request The authentication request containing user credentials.
   * @return An AuthenticationResponse object containing the generated JWT access token.
   *         Returns null if authentication fails.
   */
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
