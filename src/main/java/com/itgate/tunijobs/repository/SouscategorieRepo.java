package com.itgate.tunijobs.repository;

import com.itgate.tunijobs.models.Commande;
import com.itgate.tunijobs.models.Souscategorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SouscategorieRepo extends JpaRepository<Souscategorie, Long> {
    List<Souscategorie> findByCategorieId(Long categorieId);
}
