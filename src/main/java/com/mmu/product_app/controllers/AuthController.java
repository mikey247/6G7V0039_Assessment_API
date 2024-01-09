package com.mmu.product_app.controllers;

import com.mmu.product_app.models.User;
import com.mmu.product_app.services.JwtService;
import com.mmu.product_app.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.mmu.product_app.services.AuthService;
import com.mmu.product_app.utils.AuthenticationRequest;
import com.mmu.product_app.utils.AuthenticationResponse;
import com.mmu.product_app.utils.RegisterRequest;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;

/**
 * Controller class for handling authentication-related endpoints.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@CrossOrigin({"http://localhost:5173", "https://6g7v0039-assessment-frontend.vercel.app"})
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final JwtService jwtService;

    /**
     * Endpoint for user registration.
     *
     * @param user The registration request containing user details.
     * @return The authentication response containing the JWT token.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser (@RequestBody RegisterRequest user){
        AuthenticationResponse response = authService.register(user);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint for user authentication.
     *
     * @param request The authentication request containing user credentials.
     * @return The authentication response containing the JWT token.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate( @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    /**
     * Endpoint for retrieving user information by email extracted from JWT Token.
     *
     * @param headers The HTTP headers containing the authorization token.
     * @return The user information.
     */
    @GetMapping("/creds")
    public ResponseEntity<User> getUserByEmail(@RequestHeader HttpHeaders headers){
        String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Remove "Bearer " prefix
        }
        String email = jwtService.extractEmail(token);
        User user =  userService.getUser(email);
       return ResponseEntity.ok(user);
    }
}
