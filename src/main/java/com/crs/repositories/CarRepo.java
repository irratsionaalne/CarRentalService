package com.crs.repositories;

import com.crs.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarRepo extends JpaRepository<Car, UUID> {
}
