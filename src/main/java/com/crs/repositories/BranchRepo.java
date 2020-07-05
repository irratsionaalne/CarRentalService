package com.crs.repositories;

import com.crs.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchRepo extends JpaRepository<Branch, Long> {
    Optional<Branch> findByCityAndStreetAddress(String city, String streetadress);


}
