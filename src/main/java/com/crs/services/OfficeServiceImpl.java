package com.crs.services;

import com.crs.models.Office;
import com.crs.repositories.OfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeRepo officeRepo;

    @Override
    public boolean createOffice(Office office) throws Exception {
        if (office == null) {
            throw new Exception("Invalid office");
        }

        office.setActive(true);
        officeRepo.save(office);
        return true;
    }

    @Override
    public boolean updateOffice(Office office) {
        if (office == null || !officeRepo.existsById(office.getId())) {
            return false;
        }

        officeRepo.saveAndFlush(office);
        return true;
    }

    @Override
    public List<Office> getAllOffices() {
        return officeRepo.findAll();
    }

    @Override
    public Office getById(Long officeId) {
        return officeRepo.getOne(officeId);
    }

}
