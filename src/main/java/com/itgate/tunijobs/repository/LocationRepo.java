package com.itgate.tunijobs.repository;

import com.itgate.tunijobs.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location, Long> {
}
