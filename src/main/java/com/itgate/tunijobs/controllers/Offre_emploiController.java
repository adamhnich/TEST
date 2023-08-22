package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.Services.ProfilService;
import com.itgate.tunijobs.models.Offre_emploi;
import com.itgate.tunijobs.Services.Offre_emploiService;
import com.itgate.tunijobs.models.Profil;
import com.itgate.tunijobs.models.Vendeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Offre_emploi")
@CrossOrigin("*")
public class Offre_emploiController {

    @Autowired
    Offre_emploiService offre_emploiService;
    @Autowired
    ProfilService profilService;


    @GetMapping
    public List<Offre_emploi> getall(){
        return offre_emploiService.getall() ;    }

    @GetMapping("/{id}")
    public Offre_emploi getOneOffre_emploi(@PathVariable Long id){
        return offre_emploiService.getOneOffre_emploiService(id);
    }

/*
    @GetMapping("/{offre_emploiId}/Profil")
    public List<Profil> getProfilByIdOffre_emploi(@PathVariable Long offre_emploiId) {
        return profilService.getProfilByIdOffre_emploi(offre_emploiId);
    }  */


    @PostMapping
    public Offre_emploi createOffre_emploi (@RequestBody Offre_emploi o){
        return offre_emploiService.createOffre_emploiService(o);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOffre_emploi (@PathVariable Long id) {
        return offre_emploiService.deleteOffre_emploiService(id);

    }
    @PutMapping("/{id}")
    public Offre_emploi updateOffre_emploi (@RequestBody Offre_emploi o, @PathVariable Long id) {
        return offre_emploiService.updateOffre_emploiService(o,id);
    }

}
