package com.example.RideSharingApplication.services.implementations;

import com.example.RideSharingApplication.dto.DriverDto;
import com.example.RideSharingApplication.dto.RideDto;
import com.example.RideSharingApplication.dto.RiderDto;
import com.example.RideSharingApplication.services.DriverService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DriverServiceImpl implements DriverService {
    @Override
    public RiderDto acceptRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return null;
    }
}
