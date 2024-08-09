package com.example.RideSharingApplication.dto;

import com.example.RideSharingApplication.entities.Driver;
import com.example.RideSharingApplication.entities.Rider;
import com.example.RideSharingApplication.entities.enums.PaymentMethod;
import com.example.RideSharingApplication.entities.enums.RideStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDto {
    private Long id;


    private Point pickupLocation;
    private Point dropOffLocation;



    private LocalDateTime createdTime;


    private RiderDto rider;

    private DriverDto driver;


    private PaymentMethod paymentMethod;


    private RideStatus rideRequestStatus;

    private Double fare;
    private LocalDateTime startedTime;
    private LocalDateTime endedTime;
}
