package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.models.Avis;
import com.itgate.tunijobs.Services.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Avis")
@CrossOrigin("*")
public class AvisController {

    @Autowired
    AvisService avisService;

    @GetMapping
    public List<Avis> getall(){
        return avisService.getall() ;    }


    @PostMapping
    public Avis createAvis (@RequestBody Avis a){
        return avisService.createAvisService(a);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAvis (@PathVariable Long id) {
        return avisService.deleteAvisService(id);

    }
    @PutMapping("/{id}")
    public Avis updateRecruteur (@RequestBody Avis a, @PathVariable Long id) {
        return avisService.updateAvisService(a,id);
    }
}
