package com.example.RideSharingApplication.strategies;

import com.example.RideSharingApplication.entities.Driver;
import com.example.RideSharingApplication.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

     List<Driver> findMatchingDrivers(RideRequest rideRequest);
}
