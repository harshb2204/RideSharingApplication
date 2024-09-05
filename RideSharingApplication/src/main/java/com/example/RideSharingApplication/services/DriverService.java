package com.example.RideSharingApplication.services;

import com.example.RideSharingApplication.dto.DriverDto;
import com.example.RideSharingApplication.dto.RideDto;
import com.example.RideSharingApplication.dto.RiderDto;
import com.example.RideSharingApplication.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {


    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId, String otp);
    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId, Integer rating);

    DriverDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Driver getCurrentDriver();

    Driver updateDriverAvailability(Driver driver, boolean avaialable);
}
