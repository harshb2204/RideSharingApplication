package com.example.RideSharingApplication.strategies.implementations;

import com.example.RideSharingApplication.dto.RideRequestDto;
import com.example.RideSharingApplication.strategies.RideFareCalculationStrategy;

public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
