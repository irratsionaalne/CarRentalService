package com.crs.repositories;

import com.crs.models.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepo extends JpaRepository<Office, Long> {
}
