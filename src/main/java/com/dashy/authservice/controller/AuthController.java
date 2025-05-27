package com.dashy.authservice.controller;

import com.dashy.authservice.dto.RegisterRequest;
import com.dashy.authservice.exception.AuthException;
import com.dashy.authservice.exception.ErrorResponse;
import com.dashy.authservice.exception.SuccessResponse;
import com.dashy.authservice.model.User;
import com.dashy.authservice.service.AuthService;
import com.dashy.authservice.service.EmailService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    // All Process normal (without camunda)
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.registerUser(request);
        return ResponseEntity.ok(new SuccessResponse("User registered successfully", user));
    }
    /* All Process normal (with camunda)
    /* /verify
    /* /send-otp-validation
    /* /send-email-validation
    /* /validate-email
    /*/

    @GetMapping("/verify")
    public ResponseEntity<?> verify() {
        logger.info("\n End Point was called : /verify \n ");
        return ResponseEntity.ok(new SuccessResponse("User verifiyed successfully", ""));
    }

    @PostMapping("/send-otp-validation")
    public ResponseEntity<?> SendOTPValidation(){
        logger.info("\n End Point was called : /send-otp-validation \n ");
        return ResponseEntity.ok(
                new SuccessResponse(
                        "Verification OTP Sended", "Done"));
    }

    @PostMapping("/send-email-validation")
    public ResponseEntity<?> sendEmailValidation(@RequestParam String email) {
        logger.info("\n End Point was called : /send-email-validation \n request :" + email + "\n");

        emailService.sendVerificationEmail(email, "TOKEN_NULL");

        return ResponseEntity.ok(
                new SuccessResponse("Verification Mail Sent", "Done"));
    }

    @PostMapping("/validate-email")
    public ResponseEntity<?> ValidateEmail(@RequestParam String email){
        logger.info("\n End Point was called : /validate-email \n email : " + email);
        try {
            if(email.equalsIgnoreCase(""))
                return ResponseEntity
                        .status(HttpStatus.CONFLICT) // 409
                        .body(new ErrorResponse("Error","no email provide"));
            authService.ValidateEmail(email);
            return ResponseEntity.ok(
                    new SuccessResponse("Email is available", "OK")
            );
        } catch (AuthException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT) // 409
                    .body(new ErrorResponse("Error", e.getMessage()));
        }
    }
}
