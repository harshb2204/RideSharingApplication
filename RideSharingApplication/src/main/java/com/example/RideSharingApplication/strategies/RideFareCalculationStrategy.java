package com.example.RideSharingApplication.strategies;

import com.example.RideSharingApplication.entities.RideRequest;

public interface RideFareCalculationStrategy {

     double RIDE_FARE_MULTIPLIER = 10;

    double calculateFare(RideRequest rideRequest);
}
