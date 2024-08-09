package com.example.RideSharingApplication.services;

import com.example.RideSharingApplication.dto.DriverDto;
import com.example.RideSharingApplication.dto.RideDto;
import com.example.RideSharingApplication.dto.RiderDto;
import com.example.RideSharingApplication.entities.Ride;

import java.util.List;

public interface DriverService {


    RiderDto acceptRide(Long rideId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId);
    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId, Integer rating);

    DriverDto getMyProfile();

    List<RideDto> getAllMyRides();
}
