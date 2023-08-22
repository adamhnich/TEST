package com.itgate.tunijobs.repository;

import com.itgate.tunijobs.models.User;
import com.itgate.tunijobs.models.Vendeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendeurRepo extends JpaRepository<Vendeur, Long> {
    Optional<Vendeur> findByUsername(String username);

}
