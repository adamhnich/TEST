package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.models.Categorie;
import com.itgate.tunijobs.Services.CategorieService;
import com.itgate.tunijobs.models.Commande;
import com.itgate.tunijobs.repository.CategorieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Categorie")
@CrossOrigin("*")
public class CategorieController {

    @Autowired
    CategorieService categorieService;
    @Autowired
    CategorieRepo categorieRepo;

    @GetMapping
    public List<Categorie> getall(){
        return categorieService.getall() ;    }

    @GetMapping ("/getone/{id}")
    public Categorie getOneCategorie(@PathVariable Long id){
        return categorieRepo.findById(id).orElse(null);
    }

    @PostMapping
    public Categorie createCategorie (@RequestBody Categorie c){
        return categorieService.createCategorieService(c);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategorie (@PathVariable Long id) {
        return categorieService.deleteCategorieService(id);

    }

    @PutMapping("/{id}")
    public Categorie updateCategorie (@RequestBody Categorie c, @PathVariable Long id){

        return categorieService.updateCategorieService(c,id);



    }
}
