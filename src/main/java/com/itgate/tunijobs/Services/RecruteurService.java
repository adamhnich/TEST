package com.itgate.tunijobs.Services;


import com.itgate.tunijobs.models.Recruteur;
import com.itgate.tunijobs.models.Vendeur;
import com.itgate.tunijobs.repository.RecruteurRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruteurService {

    @Autowired
    RecruteurRepo recruteurRepo;

    @Autowired
    private EntityManager entityManager;

    public List<Recruteur> getall(){
        return recruteurRepo.findAll() ;    }

    public Recruteur createRecruteurService ( Recruteur r){
        return recruteurRepo.save(r);
    }

    public Recruteur getOneRecruteurService(Long id){
        return recruteurRepo.findById(id).orElse(null);
    }

    public ResponseEntity deleteRecruteurService (Long id) {
        recruteurRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Recruteur updateRecruteurService ( Recruteur r,  Long id){
        Recruteur r1 = recruteurRepo.findById(id).orElse(null);
        if (r1 !=null){
            r.setId(id);
            return recruteurRepo.saveAndFlush(r);
        }
        else{
            throw new RuntimeException("fail");
        }

    }

    public Long countRecruteurProfiles() {
        String jpql = "SELECT COUNT(r) FROM Recruteur r";
        Query query = entityManager.createQuery(jpql);
        return (Long) query.getSingleResult();
    }
}
