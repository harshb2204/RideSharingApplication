package com.example.RideSharingApplication.entities;

import com.example.RideSharingApplication.entities.enums.PaymentMethod;
import com.example.RideSharingApplication.entities.enums.RideRequestStatus;
import com.example.RideSharingApplication.entities.enums.RideStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity

public class Ride {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(columnDefinition = "Geometry(Point, 4326)")
//    The @Column(columnDefinition = "Geometry(Point, 4326)") annotation is used to specify that the column should be of type
//    Geometry(Point) with the SRID (Spatial Reference System Identifier) 4326. This SRID corresponds to the WGS 84 coordinate
//    system,which is a standard for latitude and longitude coordinates used globally, especially in GPS.
    private Point pickupLocation;
    private Point dropOffLocation;

    @CreationTimestamp

    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;
    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideStatus rideRequestStatus;

    private Double fare;
    private LocalDateTime startedTime;
    private LocalDateTime endedTime;
}
