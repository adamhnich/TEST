package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.Categorie;
import com.itgate.tunijobs.repository.CategorieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {

    @Autowired
    CategorieRepo categorieRepo;

    public List<Categorie> getall(){
        return categorieRepo.findAll() ;    }

    public Categorie createCategorieService ( Categorie c){
        return categorieRepo.save(c);
    }

    public ResponseEntity deleteCategorieService (Long id) {
        categorieRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Categorie updateCategorieService ( Categorie c,  Long id){
        Categorie c1 = categorieRepo.findById(id).orElse(null);
        if (c1 !=null){
            c.setId(id);
            return categorieRepo.saveAndFlush(c);
        }
        else{
            throw new RuntimeException("fail");
        }

    }

}
