package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.Commande;
import com.itgate.tunijobs.models.Offre_emploi;
import com.itgate.tunijobs.models.Vendeur;
import com.itgate.tunijobs.repository.Offre_emploiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Offre_emploiService {

    @Autowired
    Offre_emploiRepo offre_emploiRepo;

    public List<Offre_emploi> getall() {
        return offre_emploiRepo.findAll();
    }

    public Offre_emploi getOneOffre_emploiService(Long id){
        return offre_emploiRepo.findById(id).orElse(null);
    }

    public Offre_emploi createOffre_emploiService(Offre_emploi o) {
        return offre_emploiRepo.save(o);
    }

    public List<Offre_emploi> getOffre_emploiByIdRecruteur(Long recruteurId) {
        return offre_emploiRepo.findByrecruteurId(recruteurId);
    }

    public ResponseEntity deleteOffre_emploiService(Long id) {
        offre_emploiRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Offre_emploi updateOffre_emploiService(Offre_emploi o, Long id) {
        Offre_emploi o1 = offre_emploiRepo.findById(id).orElse(null);
        if (o1 != null) {
            o.setId(id);
            return offre_emploiRepo.saveAndFlush(o);
        } else {
            throw new RuntimeException("fail");
        }

    }
}
