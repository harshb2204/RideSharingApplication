package com.example.RideSharingApplication.services;

import com.example.RideSharingApplication.entities.RideRequest;

public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);


}
