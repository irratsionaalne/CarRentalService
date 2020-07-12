package com.crs.repositories;

import com.crs.models.Booking;
import com.crs.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepo extends JpaRepository<Booking, UUID> {

    @Query("SELECT b from Booking b where b.dateFrom >= :startTime and b.dateTo <= :endTime and b.car = :car")
    public List<Booking> findBookingByDateAndCar(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("car") Car car);

}
