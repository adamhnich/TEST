package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.models.Reclamation;
import com.itgate.tunijobs.Services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Reclamation")
@CrossOrigin("*")
public class ReclamationController {

    @Autowired
    ReclamationService reclamationService;

    @GetMapping
    public List<Reclamation> getall() {
        return reclamationService.getall();
    }


    @PostMapping("/{id_user}")
    public Reclamation creatReclamation(@RequestBody Reclamation r, @PathVariable Long id_user) {
        return reclamationService.creatReclamationService(r, id_user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReclamation(@PathVariable Long id) {
        return reclamationService.deleteReclamationService(id);
    }

    @PutMapping("/{id}")
    public Reclamation updateReclamation(@RequestBody Reclamation r, @PathVariable Long id) {

        return reclamationService.updateReclamationService(r,id);


    }

    @GetMapping("/{id}")
    public Reclamation getOneReclamation(@PathVariable Long id){
        return reclamationService.getOneReclamationService(id);
    }
}
