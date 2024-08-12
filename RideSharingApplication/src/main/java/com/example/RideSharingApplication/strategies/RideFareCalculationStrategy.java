package com.example.RideSharingApplication.strategies;

import com.example.RideSharingApplication.dto.RideRequestDto;

public interface RideFareCalculationStrategy {

    double calculateFare(RideRequestDto rideRequestDto);
}
