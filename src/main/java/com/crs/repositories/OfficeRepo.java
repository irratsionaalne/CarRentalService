package com.crs.repositories;

import com.crs.models.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfficeRepo extends JpaRepository<Office, UUID> {
}
