package com.mmu.product_app.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.mmu.product_app.models.User;
import com.mmu.product_app.services.AuthService;
import com.mmu.product_app.utils.AuthenticationRequest;
import com.mmu.product_app.utils.AuthenticationResponse;
import com.mmu.product_app.utils.RegisterRequest;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@CrossOrigin({"http://localhost:5173/", "https://6g7v0039-assessment-frontend.vercel.app/"})
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser (@RequestBody RegisterRequest user){
        AuthenticationResponse response = authService.register(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate( @RequestBody AuthenticationRequest request) {
        System.out.println(request.getEmail());
        System.out.println(request.getPassword());
        return ResponseEntity.ok(authService.authenticate(request));
    }
    
}
