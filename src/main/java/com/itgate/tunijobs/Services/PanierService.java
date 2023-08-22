package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.Panier;
import com.itgate.tunijobs.repository.PanierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanierService {
    @Autowired
    PanierRepo panierRepo;

    public List<Panier> getall(){
        return panierRepo.findAll() ;    }

    public Panier createPanierService ( Panier p){
        return panierRepo.save(p);
    }

    public ResponseEntity deletePanierSerrvice (Long id) {
        panierRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
    public Panier updatePanierSevice ( Panier p,  Long id){
        Panier p1 = panierRepo.findById(id).orElse(null);
        if (p1 !=null){
            p.setId(id);
            return panierRepo.saveAndFlush(p);
        }
        else{
            throw new RuntimeException("fail");
        }


    }
}
