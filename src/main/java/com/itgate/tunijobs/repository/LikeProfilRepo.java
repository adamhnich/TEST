package com.itgate.tunijobs.repository;


import com.itgate.tunijobs.models.LikeProfil;
import com.itgate.tunijobs.models.Profil;
import com.itgate.tunijobs.models.Recruteur;
import com.itgate.tunijobs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeProfilRepo extends JpaRepository<LikeProfil, Long> {

    //  LikeProfil findLikeByIdProfilAndIdRecruteur(Profil profil , Recruteur recruteur);
  //LikeProfil addLikeProfil(Long idprofil,Long idrecruteur);
}
