package com.crs.repositories;

import com.crs.models.CarRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarRentalRepo extends JpaRepository<CarRental, UUID> {
}
