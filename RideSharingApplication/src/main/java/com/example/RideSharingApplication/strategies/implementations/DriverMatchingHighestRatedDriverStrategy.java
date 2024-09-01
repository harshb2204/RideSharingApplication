package com.example.RideSharingApplication.strategies.implementations;

import com.example.RideSharingApplication.entities.Driver;
import com.example.RideSharingApplication.entities.RideRequest;
import com.example.RideSharingApplication.repositories.DriverRepository;
import com.example.RideSharingApplication.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
    private final DriverRepository driverRepository;
    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
    }
}
