package com.crs.services;

import com.crs.models.Office;

import java.util.List;
import java.util.UUID;

public interface OfficeService {

    boolean createOffice(Office office) throws Exception;

    boolean updateOffice(Office office);

    List<Office> getAllOffices();

    Office getById(UUID officeId);

}
