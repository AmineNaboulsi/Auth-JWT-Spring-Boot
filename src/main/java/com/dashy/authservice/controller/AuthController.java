package com.dashy.authservice.controller;

import com.dashy.authservice.dto.RegisterRequest;
import com.dashy.authservice.model.User;
import com.dashy.authservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) throws Exception {
        return ResponseEntity.ok(authService.registerUser(request));
    }
    @GetMapping("/test")
    public String Test() {
        return "test";
    }
}
