package com.example.RideSharingApplication.strategies.implementations;

import com.example.RideSharingApplication.dto.RideRequestDto;
import com.example.RideSharingApplication.entities.Driver;
import com.example.RideSharingApplication.strategies.DriverMatchingStrategy;

import java.util.List;

public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDrivers(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
