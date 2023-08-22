package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.models.Reservation;
import com.itgate.tunijobs.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Reservation")
@CrossOrigin("*")
public class ReservationController {
    @Autowired
    ReservationService reservationService;


    @GetMapping
    public List<Reservation> getall() {
        return reservationService.getall();
    }


    @PostMapping
    public Reservation createReservation(@RequestBody Reservation r) {
        return reservationService.createReservationService(r);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReservation(@PathVariable Long id) {
        return reservationService.deleteReservationService(id);

    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@RequestBody Reservation r, @PathVariable Long id) {
        return reservationService.updateReservationService(r, id);

    }
}
