package com.example.RideSharingApplication.strategies;

import com.example.RideSharingApplication.dto.RideRequestDto;
import com.example.RideSharingApplication.entities.Driver;

import java.util.List;

public interface DriverMatchingStrategy {

     List<Driver> findMatchingDrivers(RideRequestDto rideRequestDto);
}
