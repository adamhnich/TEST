package com.itgate.tunijobs.repository;

import com.itgate.tunijobs.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
}
