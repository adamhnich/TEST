package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.models.Livraison;
import com.itgate.tunijobs.Services.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Livraison")
@CrossOrigin("*")
public class LivraisonController {

    @Autowired
    LivraisonService livraisonService;
    @GetMapping
    public List<Livraison> getall(){
        return livraisonService.getall() ;    }
    @PostMapping
    public Livraison createLivraison (@RequestBody Livraison l){
        return livraisonService.createLivraisonService(l);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteLivraison (@PathVariable Long id) {
        return livraisonService.deleteLivraisonService(id);

    }
    @PutMapping("/{id}")
    public Livraison updateLivraison (@RequestBody Livraison l, @PathVariable Long id) {
        return livraisonService.updateLivraisonService(l, id);
    }
}
