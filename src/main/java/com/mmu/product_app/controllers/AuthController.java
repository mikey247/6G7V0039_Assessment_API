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


@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@CrossOrigin({"http://localhost:5173", "https://6g7v0039-assessment-frontend.vercel.app"})
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser (@RequestBody RegisterRequest user){
        AuthenticationResponse response = authService.register(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate( @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

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
