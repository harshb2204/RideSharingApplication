package com.example.RideSharingApplication.services;

import com.example.RideSharingApplication.entities.Driver;
import com.example.RideSharingApplication.entities.Ride;
import com.example.RideSharingApplication.entities.RideRequest;
import com.example.RideSharingApplication.entities.Rider;
import com.example.RideSharingApplication.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);



    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
