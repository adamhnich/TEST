package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.Vendeur;
import com.itgate.tunijobs.repository.VendeurRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendeurService {
    @Autowired
    VendeurRepo vendeurRepo;
    @Autowired
    private EntityManager entityManager;

    public List<Vendeur> getall(){
        return vendeurRepo.findAll() ;    }

    public  Vendeur getOneVendeurService(Long id){
        return vendeurRepo.findById(id).orElse(null);
    }

    public Vendeur createVendeurService ( Vendeur v){
        return vendeurRepo.save(v);
    }

    public ResponseEntity deleteVendeurService (Long id) {
        vendeurRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Vendeur updateVendeurService ( Vendeur v,  Long id){
        Vendeur v1 = vendeurRepo.findById(id).orElse(null);
        if (v1 !=null){
            v.setId(id);
            return vendeurRepo.saveAndFlush(v);
        }
        else{
            throw new RuntimeException("fail");
        }

    }

    public Long countVendeurProfiles() {
        String jpql = "SELECT COUNT(v) FROM Vendeur v";
        Query query = entityManager.createQuery(jpql);
        return (Long) query.getSingleResult();
    }

}
