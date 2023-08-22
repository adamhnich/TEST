package com.itgate.tunijobs.repository;

import com.itgate.tunijobs.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepo extends JpaRepository<Commande, Long> {
    List<Commande> findByClientId(Long clientId);

}
