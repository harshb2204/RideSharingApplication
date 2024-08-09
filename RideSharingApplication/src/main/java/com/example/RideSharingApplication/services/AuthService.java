package com.example.RideSharingApplication.services;

import com.example.RideSharingApplication.dto.DriverDto;
import com.example.RideSharingApplication.dto.SignUpDto;
import com.example.RideSharingApplication.dto.UserDto;
import com.example.RideSharingApplication.entities.Driver;

public interface AuthService  {

    String login(String email, String password);

    UserDto signUp(SignUpDto signUpDto);

    DriverDto onboardNewDriver(Long userId);

}
