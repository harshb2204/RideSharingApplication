package com.example.RideSharingApplication.repositories;

import com.example.RideSharingApplication.entities.Payment;
import com.example.RideSharingApplication.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRide(Ride ride);
}
