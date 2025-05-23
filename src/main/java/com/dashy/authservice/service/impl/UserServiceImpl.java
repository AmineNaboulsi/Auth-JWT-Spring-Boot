package com.dashy.authservice.service.impl;

import com.dashy.authservice.dto.RegisterRequest;
import com.dashy.authservice.exception.AuthException;
import com.dashy.authservice.model.User;
import com.dashy.authservice.repository.UserRepository;
import com.dashy.authservice.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public User registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new AuthException("User with email " + request.email() + " already exists");
        }

        if (userRepository.findByPhoneNumber(request.phoneNumber()).isPresent()) {
            throw new AuthException("Phone number " + request.phoneNumber() + " already in use");
        }
        User user = new User();
        user.setUserName(request.userName());
        user.setEmail(request.email());
        user.setPhoneNumber(request.phoneNumber());
        user.setPassword(request.password());
        user.setEmailVerification(false);
        user.setPhoneVerification(false);
        return userRepository.save(user);
    }

//    public User register(String email, String phoneNumber) throws AuthException {
//        if (userRepository.findByEmail(email).isPresent()) {
//            throw new AuthException("User with email " + email + " already exists");
//        }
//        if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) {
//            throw new AuthException("Phone number " + phoneNumber + " already in use");
//        }
//        User user = new User();
//        user.setId(java.util.UUID.randomUUID());
//        user.setEmail(email);
//        user.setPhoneNumber(phoneNumber);
//        user.setEmailVerified(false);
//        user.setPhoneVerified(false);
//        return userRepository.save(user);
//    }
}
