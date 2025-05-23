package com.dashy.authservice.service;

import com.dashy.authservice.dto.RegisterRequest;
import com.dashy.authservice.model.User;

public interface AuthService {
    User registerUser(RegisterRequest request);
}
