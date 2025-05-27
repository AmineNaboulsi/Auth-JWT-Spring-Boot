package com.dashy.authservice.service.impl;

import com.dashy.authservice.dto.RegisterRequest;
import com.dashy.authservice.exception.AuthException;
import com.dashy.authservice.model.User;
import com.dashy.authservice.repository.UserRepository;
import com.dashy.authservice.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User registerUser(RegisterRequest request) {
        //
        ValidateUserName(request.userName());
        ValidateEmail(request.email());
        ValidatePhoneNumber(request.phone());
        //
        User user = new User();
        user.setUserName(request.userName());
        user.setEmail(request.email());
        user.setPhoneNumber(request.phone());
        user.setPassword(encoder.encode(request.password()));
        user.setEmailVerification(false);
        user.setPhoneVerification(false);
        return userRepository.save(user);
    }

    /*
    /*
    /*
    /*/
    @Override
    public boolean ValidateUserName(String userName){
        userRepository.findByUserName(userName).ifPresent(
                user -> {
                    throw new AuthException("User with name " + userName + " already in use");
                });
        return true;
    }
    /*
    /*
    /*
    /*/
    @Override
    public boolean ValidateEmail(String email){
        userRepository.findByEmail(email).ifPresent(
                user -> {
                    throw new AuthException("User with email " + email + " already exists");
                });
        return true;
    }
    /*
    /*
    /*
    /*/
    @Override
    public boolean ValidatePhoneNumber(String PhoneNumber){
        userRepository.findByPhoneNumber(PhoneNumber).ifPresent(
                user -> {
                    throw new AuthException("Phone number" + PhoneNumber + " already in use") ;
                }
        );
        return true;
    }

}