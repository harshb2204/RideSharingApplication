package com.example.RideSharingApplication.strategies.implementations;

import com.example.RideSharingApplication.entities.RideRequest;
import com.example.RideSharingApplication.services.DistanceService;
import com.example.RideSharingApplication.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {



    private final DistanceService distanceService;
    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropOffLocation());
        return distance * RIDE_FARE_MULTIPLIER;

    }
}
