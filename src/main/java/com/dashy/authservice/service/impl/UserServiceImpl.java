package com.dashy.authservice.service.impl;

import com.dashy.authservice.dto.RegisterRequest;
import com.dashy.authservice.exception.AuthException;
import com.dashy.authservice.model.User;
import com.dashy.authservice.repository.UserRepository;
import com.dashy.authservice.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User registerUser(RegisterRequest request) {
        userRepository.findByUserName(request.userName()).ifPresent(
                user -> {
                        throw new AuthException("User with name " + request.userName() + " already in use");
                });
        userRepository.findByEmail(request.email()).ifPresent(
                user -> {
                    throw new AuthException("User with email " + request.email() + " already exists");
                });
        userRepository.findByPhoneNumber(request.phoneNumber()).ifPresent(
                user -> {
                    throw new AuthException("Phone number" + request.phoneNumber() + " already in use") ;
                }
        );
        User user = new User();
        user.setUserName(request.userName());
        user.setEmail(request.email());
        user.setPhoneNumber(request.phoneNumber());
        user.setPassword(encoder.encode(request.password()));
        user.setEmailVerification(false);
        user.setPhoneVerification(false);
        return userRepository.save(user);
    }

}
