package com.itgate.tunijobs.repository;

import com.itgate.tunijobs.models.Commande;
import com.itgate.tunijobs.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepo extends JpaRepository<Produit, Long> {
    List<Produit> findByVendeurId(Long vendeurId);
}
