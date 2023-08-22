package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.Reservation;
import com.itgate.tunijobs.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationRepo reservationRepo;

    public List<Reservation> getall(){
        return reservationRepo.findAll() ;    }


    public Reservation createReservationService ( Reservation r){
        return reservationRepo.save(r);
    }

    public ResponseEntity deleteReservationService (Long id) {
        reservationRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Reservation updateReservationService ( Reservation r, Long id){
        Reservation r1 = reservationRepo.findById(id).orElse(null);
        if (r1 !=null){
            r.setId(id);
            return reservationRepo.saveAndFlush(r);
        }
        else{
            throw new RuntimeException("fail");
        }

    }

}
