package com.crs.services;

import com.crs.models.Office;

import java.util.List;

public interface OfficeService {

    boolean createOffice(Office office) throws Exception;

    boolean updateOffice(Office office);

    List<Office> getAllOffices();

    Office getById(Long officeId);

}
