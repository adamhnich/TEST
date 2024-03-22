package com.itgate.tunijobs.repository;


import com.itgate.tunijobs.models.Commande;
import com.itgate.tunijobs.models.Offre_emploi;
import com.itgate.tunijobs.models.Popularite;
import com.itgate.tunijobs.models.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProfilRepo extends JpaRepository<Profil, Long> {
    //List<Profil> findByOffre(Long Offre_emploi_id);
    Profil findByEmployeId(Long employeId);

}
