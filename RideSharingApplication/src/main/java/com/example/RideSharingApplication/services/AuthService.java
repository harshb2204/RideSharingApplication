package com.example.RideSharingApplication.services;


import com.example.RideSharingApplication.dto.DriverDto;
import com.example.RideSharingApplication.dto.SignupDto;
import com.example.RideSharingApplication.dto.UserDto;

public interface AuthService {

    String login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId);
}
