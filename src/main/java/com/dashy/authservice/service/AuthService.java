package com.dashy.authservice.service;

import com.dashy.authservice.dto.RegisterRequest;
import com.dashy.authservice.model.User;

public interface AuthService {
    User registerUser(RegisterRequest request);
    boolean ValidatePhoneNumber(String PhoneNumber);
    boolean ValidateEmail(String email);
    boolean ValidateUserName(String userName);
}
