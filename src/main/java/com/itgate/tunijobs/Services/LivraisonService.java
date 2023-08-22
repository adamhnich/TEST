package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.Livraison;
import com.itgate.tunijobs.repository.LivraisonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivraisonService {
    @Autowired
    LivraisonRepo livraisonRepo;

    public List<Livraison> getall() {
        return livraisonRepo.findAll();
    }

    public Livraison createLivraisonService(Livraison l) {
        return livraisonRepo.save(l);
    }

    public ResponseEntity deleteLivraisonService(Long id) {
        livraisonRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Livraison updateLivraisonService(Livraison l, Long id) {
        Livraison l1 = livraisonRepo.findById(id).orElse(null);
        if (l1 != null) {
            l.setId(id);
            return livraisonRepo.saveAndFlush(l);
        } else {
            throw new RuntimeException("fail");
        }

    }

}
