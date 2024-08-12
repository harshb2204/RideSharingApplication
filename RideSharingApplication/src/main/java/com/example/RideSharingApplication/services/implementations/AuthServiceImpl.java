package com.example.RideSharingApplication.services.implementations;

import com.example.RideSharingApplication.dto.DriverDto;
import com.example.RideSharingApplication.dto.SignUpDto;
import com.example.RideSharingApplication.dto.UserDto;
import com.example.RideSharingApplication.services.AuthService;
import org.springframework.stereotype.Service;


@Service

public class AuthServiceImpl implements AuthService {

    @Override
    public String login(String email, String password) {
        return null;
    }

    @Override
    public UserDto signUp(SignUpDto signUpDto) {
        return null;
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
