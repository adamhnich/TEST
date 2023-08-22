package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.models.Location;
import com.itgate.tunijobs.Services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Location")
@CrossOrigin("*")
public class LocationController {
    @Autowired
    LocationService locationService;

    @GetMapping
    public List<Location> getall() {
        return locationService.getall();
    }

    @GetMapping("/{id}")
    public Location getOneLocation(@PathVariable Long id) {
        return locationService.getOneLocationService(id);
    }

    @PostMapping
    public Location createLocation(@RequestBody Location ltn) {
        return locationService.createLocationService(ltn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLocation(@PathVariable Long id) {
        return locationService.deleteLocationService(id);

    }

    @PutMapping("/{id}")
    public Location updateLocation(@RequestBody Location ltn, @PathVariable Long id) {

        return locationService.updateLocationService(ltn, id);

    }
}
