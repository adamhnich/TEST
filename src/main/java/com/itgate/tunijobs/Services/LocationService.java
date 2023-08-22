package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.Location;
import com.itgate.tunijobs.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    LocationRepo locationRepo;


    public List<Location> getall() {
        return locationRepo.findAll();
    }

    public Location getOneLocationService(Long id) {
        return locationRepo.findById(id).orElse(null);
    }

    public Location createLocationService(Location ltn) {
        return locationRepo.save(ltn);
    }

    public ResponseEntity deleteLocationService(Long id) {
        locationRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Location updateLocationService(Location ltn, Long id) {
        Location ltn1 = locationRepo.findById(id).orElse(null);
        if (ltn1 != null) {
            ltn.setId(id);
            return locationRepo.saveAndFlush(ltn);
        } else {
            throw new RuntimeException("fail");
        }

    }
}
