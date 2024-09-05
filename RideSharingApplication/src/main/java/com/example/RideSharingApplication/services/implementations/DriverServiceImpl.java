package com.example.RideSharingApplication.services.implementations;

import com.example.RideSharingApplication.dto.DriverDto;
import com.example.RideSharingApplication.dto.RideDto;
import com.example.RideSharingApplication.dto.RiderDto;
import com.example.RideSharingApplication.entities.Driver;
import com.example.RideSharingApplication.entities.Ride;
import com.example.RideSharingApplication.entities.RideRequest;
import com.example.RideSharingApplication.entities.enums.RideRequestStatus;
import com.example.RideSharingApplication.entities.enums.RideStatus;
import com.example.RideSharingApplication.exceptions.ResourceNotFoundException;
import com.example.RideSharingApplication.repositories.DriverRepository;
import com.example.RideSharingApplication.services.DriverService;
import com.example.RideSharingApplication.services.PaymentService;
import com.example.RideSharingApplication.services.RideRequestService;
import com.example.RideSharingApplication.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;

    private final DriverRepository driverRepository;

    private final ModelMapper modelMapper;

    private final RideService rideService;

    private final PaymentService paymentService;
    @Override
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);

        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("Ride request cannot be accepted status is " + rideRequest.getRideRequestStatus());
        }

        Driver currentDriver  = getCurrentDriver();
        if(!currentDriver.getAvailable()){
            throw new RuntimeException("Driver cannot accept ride due to unavailability");

        }
        Driver savedDriver = updateDriverAvailability(currentDriver, false);

        Ride ride = rideService.createNewRide(rideRequest, savedDriver);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start the ride as he has not accepted it earlier");
        }//to check if driver owns the ride

        //ride which is ongoin cannot be cancelled

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled, invalid status: " + ride.getRideStatus());
        }

        rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        updateDriverAvailability(driver, true);
        return modelMapper.map(ride, RideDto.class);

    }

    @Override
    public RideDto startRide(Long rideId, String otp) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start the ride as he has not accepted it earlier");
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride status is not confirmed hence cannot be started" + ride.getRideStatus());
        }

        if(!otp.equals(ride.getOtp())){
            throw new RuntimeException("Otp is not valid: "+otp);
        }

        ride.setStartedAt(LocalDateTime.now());

        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);

    paymentService.createNewPayment(savedRide);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    @Transactional
    public RideDto endRide(Long rideId) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start the ride as he has not accepted it earlier");
        }

        if(!ride.getRideStatus().equals(RideStatus.ONGOING)){
            throw new RuntimeException("Ride status is not ONGOING hence cannot be ended" + ride.getRideStatus());
        }
        ride.setEndedAt(LocalDateTime.now());
        Ride savedRide  = rideService.updateRideStatus(ride, RideStatus.ENDED);
        updateDriverAvailability(driver, true);
        paymentService.processPayment(ride);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {

        Driver driver = getCurrentDriver();

        return modelMapper.map(driver, DriverDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(currentDriver, pageRequest).map(ride -> modelMapper.map(ride, RideDto.class));


    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException("Driver not found with id 2"));
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean avaialable) {

        driver.setAvailable(avaialable);
        return driverRepository.save(driver);
    }
}
