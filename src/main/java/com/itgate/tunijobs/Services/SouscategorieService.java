package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.Categorie;
import com.itgate.tunijobs.models.Commande;
import com.itgate.tunijobs.models.Souscategorie;
import com.itgate.tunijobs.repository.CategorieRepo;
import com.itgate.tunijobs.repository.SouscategorieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SouscategorieService {
    @Autowired
    SouscategorieRepo souscategorieRepo;
    @Autowired
    CategorieRepo categorieRepo;

    public List<Souscategorie> getall(){
        return souscategorieRepo.findAll() ;
    }

    public Souscategorie createSouscategorieService ( Souscategorie s) {
        return souscategorieRepo.save(s);
    }
    public Souscategorie createSouscategorieService ( Souscategorie s , Long id_categorie){
        Categorie c = categorieRepo.findById(id_categorie).orElse(null);
        s.setCategorie(c);
        return souscategorieRepo.save(s);
    }

    public ResponseEntity deleteSouscategorieService (Long id) {
        souscategorieRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Souscategorie updateSouscategorieService ( Souscategorie s, Long id){
        Souscategorie s1 = souscategorieRepo.findById(id).orElse(null);
        if (s1 !=null){
            s.setId(id);
            return souscategorieRepo.saveAndFlush(s);
        }
        else{
            throw new RuntimeException("fail");
        }

    }

    public List<Souscategorie> getSousCategoriesByIdCategorie(Long categorieId) {
        return souscategorieRepo.findByCategorieId(categorieId);
    }


}
