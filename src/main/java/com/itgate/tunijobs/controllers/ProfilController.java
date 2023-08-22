package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.models.Produit;
import com.itgate.tunijobs.models.Profil;
import com.itgate.tunijobs.Services.ProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Profil")
@CrossOrigin("*")
public class ProfilController {
    @Autowired
    ProfilService profilService;

    @GetMapping
    public List<Profil> getall(){
        return profilService.getall() ;    }

    @GetMapping("/{id}")
    public Profil getOneProduit(@PathVariable Long id){
        return profilService.getOneProfilService(id);
    }


    @PostMapping
    public Profil createProfil (@RequestBody Profil p){
        return profilService.createProfilService(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProfil (@PathVariable Long id) {
        return profilService.deleteProfilService(id);

    }
    @PutMapping("/{id}")
    public Profil updateProfil (@RequestBody Profil p, @PathVariable Long id) {
        return profilService.updateProfilService(p,id);
    }
}
