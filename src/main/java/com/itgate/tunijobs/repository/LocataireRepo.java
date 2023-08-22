package com.itgate.tunijobs.repository;

import com.itgate.tunijobs.models.Locataire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocataireRepo extends JpaRepository<Locataire, Long> {
}
