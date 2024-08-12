package com.example.RideSharingApplication.repositories;

import com.example.RideSharingApplication.entities.Ride;
import com.example.RideSharingApplication.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RiderRepository extends JpaRepository<Rider, Long> {
}
