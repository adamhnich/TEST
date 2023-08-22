package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.models.Locataire;
import com.itgate.tunijobs.Services.LocataireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Locataire")
@CrossOrigin("*")
public class LocataireController {
    @Autowired
    LocataireService locataireService;

    @GetMapping
    public List<Locataire> getall(){
        return locataireService.getall() ;    }

    @GetMapping("/{id}")
    public Locataire getOneLocataire(@PathVariable Long id){
        return locataireService.getOneLocataireService(id);
    }

    @PostMapping
    public Locataire createLocataire (@RequestBody Locataire lt){
        return locataireService.createLocataireService(lt);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteLocataire (@PathVariable Long id) {
        return locataireService.deleteLocataireService(id);

    }
    @PutMapping("/{id}")
    public Locataire updateLocataire (@RequestBody Locataire lt, @PathVariable Long id){

        return locataireService.updateLocataireService(lt,id);

    }
}
