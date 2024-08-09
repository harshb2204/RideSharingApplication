package com.example.RideSharingApplication.entities;

import com.example.RideSharingApplication.entities.enums.PaymentMethod;
import com.example.RideSharingApplication.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter

public class RideRequest {

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

    private LocalDateTime requestedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideRequestStatus rideRequestStatus;



}
