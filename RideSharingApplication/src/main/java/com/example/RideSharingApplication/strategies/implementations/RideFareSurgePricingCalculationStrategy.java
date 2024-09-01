package com.example.RideSharingApplication.strategies.implementations;

import com.example.RideSharingApplication.entities.RideRequest;
import com.example.RideSharingApplication.services.DistanceService;
import com.example.RideSharingApplication.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RideFareSurgePricingCalculationStrategy implements RideFareCalculationStrategy {
    private final DistanceService distanceService;

    private static final double SURGE_FACTOR = 2;
    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropOffLocation());
        return distance * RIDE_FARE_MULTIPLIER * SURGE_FACTOR;

    }
}
