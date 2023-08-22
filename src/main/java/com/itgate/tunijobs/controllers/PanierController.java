package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.models.Panier;
import com.itgate.tunijobs.Services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Panier")
@CrossOrigin("*")
public class PanierController {

    @Autowired
    PanierService panierService;
    @GetMapping
    public List<Panier> getall(){
        return panierService.getall() ;    }

    @PostMapping
    public Panier createPanier (@RequestBody Panier p){
        return panierService.createPanierService(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePanier (@PathVariable Long id) {
        return panierService.deletePanierSerrvice(id);

    }
    @PutMapping("/{id}")
    public Panier updatePanier (@RequestBody Panier p, @PathVariable Long id){
        return panierService.updatePanierSevice(p,id);
    }
}
