package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.models.Souscategorie;
import com.itgate.tunijobs.Services.SouscategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Souscategories")
@CrossOrigin("*")
public class SouscategorieController {

    @Autowired
    SouscategorieService souscategorieService;



    @GetMapping
    public List<Souscategorie> getall() {
        return souscategorieService.getall();
    }

    @PostMapping
    public Souscategorie createSouscategorie(@RequestBody Souscategorie s) {
        return souscategorieService.createSouscategorieService(s);
    }

    @PostMapping("/{id_categorie}")
    public Souscategorie creatSouscategorie(@RequestBody Souscategorie s, @PathVariable Long id_categorie) {
        return souscategorieService.createSouscategorieService(s, id_categorie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSouscategorie(@PathVariable Long id) {
        return souscategorieService.deleteSouscategorieService(id);
    }

    @PutMapping("/{id}")
    public Souscategorie updateSouscategorie(@RequestBody Souscategorie s, @PathVariable Long id) {
        return souscategorieService.updateSouscategorieService(s, id);
    }
}
