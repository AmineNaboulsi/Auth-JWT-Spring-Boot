package com.dashy.authservice.service;

import com.dashy.authservice.exception.AuthException;

public interface EmailService {
    void sendVerificationEmail(String to, String token);

}