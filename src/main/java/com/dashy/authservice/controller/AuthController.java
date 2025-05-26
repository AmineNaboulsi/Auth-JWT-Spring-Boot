package com.dashy.authservice.controller;

import com.dashy.authservice.dto.RegisterRequest;
import com.dashy.authservice.exception.AuthException;
import com.dashy.authservice.exception.ErrorResponse;
import com.dashy.authservice.exception.SuccessResponse;
import com.dashy.authservice.model.User;
import com.dashy.authservice.service.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private static final Logger logger = LoggerFactory.getLogger(User.class);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.registerUser(request);
        return ResponseEntity.ok(new SuccessResponse("User registered successfully", user));
    }

    @GetMapping("/test")
    public String Test() {
        return "test ";
    }

    @PostMapping("/send-otp-validation")
    public ResponseEntity<?> SendOTPValidation(){
        logger.info("\n /send-otp-validation was called \n");
        return ResponseEntity.ok(
                new SuccessResponse(
                        "Verification OTP Sended", "Done"));
    }
    @PostMapping("/send-email-validation")
    public ResponseEntity<?> SendEmailValidation(){
        logger.info("\n /send-email-validation was called \n");
        return ResponseEntity.ok(
                new SuccessResponse(
                        "Verification Mail Sended", "Done"));
    }
}
