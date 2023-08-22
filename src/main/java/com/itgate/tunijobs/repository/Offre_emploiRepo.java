package com.itgate.tunijobs.repository;


import com.itgate.tunijobs.models.Commande;
import com.itgate.tunijobs.models.Offre_emploi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Offre_emploiRepo extends JpaRepository<Offre_emploi, Long> {
    List<Offre_emploi> findByrecruteurId(Long recruteurId);
}
