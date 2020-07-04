package com.crs.repositories;

import com.crs.models.CarReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarReturnRepo extends JpaRepository<CarReturn, Long> {
}