package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.*;
import com.itgate.tunijobs.repository.ProfilRepo;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfilService {

    @Autowired
    ProfilRepo profilRepo;
    @Enumerated
    Popularite popularite;

    public List<Profil> getall() {
        return profilRepo.findAll();
    }

    public Profil getOneProfilService(Long id){
        return profilRepo.findById(id).orElse(null);
    }

    public Profil createProfilService(Profil p) {
        return profilRepo.save(p);
    }

    public ResponseEntity deleteProfilService(Long id) {
        profilRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Profil getProfilByIdemploye(Long employeId) {
        return profilRepo.findByEmployeId(employeId);
    }
/*
     public List<Profil> getProfilByIdOffre_emploi(Long offre_emploiId) {
        return profilRepo.findByOffre(offre_emploiId);
    }  */



    public Profil updateProfilService(Profil p, Long id) {
        Profil p1 = profilRepo.findById(id).orElse(null);
        if (p1 != null) {
            p.setId(id);
            return profilRepo.saveAndFlush(p);
        } else {
            throw new RuntimeException("fail");
        }

    }

    public Popularite popularite(Long id) {
        Profil p = profilRepo.findById(id).orElse(null);
        if (p.getLikesNbr() >= 1000) {
            p.setPopularite(Popularite.superFamous);
            profilRepo.save(p);
        }

        if (p.getLikesNbr() >= 100) {
            p.setPopularite(Popularite.famous);
            profilRepo.save(p);
            return p.getPopularite();
        }


        if (p.getLikesNbr() >= 10) {
            p.setPopularite(Popularite.pouplar);
            profilRepo.save(p);
            return p.getPopularite();
        }
        return null;

    }






}
