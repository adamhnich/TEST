package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.models.Materiels;

import com.itgate.tunijobs.Services.MaterielsService;
import com.itgate.tunijobs.models.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/Materiels")
@CrossOrigin("*")
public class MaterielsController {

    @Autowired
    MaterielsService materielsService;

    @GetMapping
    public List<Materiels> getall(){
        return materielsService.getall() ;
    }

    @GetMapping("/{id}")
    public Materiels getOneMateriels(@PathVariable Long id){
        return materielsService.getOneMaterielsService(id);
    }

    @PostMapping
    public Materiels createMateriels (@RequestBody Materiels m){
        return materielsService.createMaterielsService(m);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMateriels (@PathVariable Long id) {
        return  materielsService.deleteMaterielsService(id); }

    @PutMapping("/{id}")
    public Materiels updateMateriels (@RequestBody Materiels m, @PathVariable Long id){
        return materielsService.updateMaterielsService(m,id); }


    @PostMapping("/upload/{id_locataire}/{id_souscategorie}")
    public ResponseEntity<ResponseMessage> uploadfilesService(@RequestParam("files") MultipartFile[] files, Materiels m,
                                                              @PathVariable Long id_souscategorie, @PathVariable Long id_locataire ) {
        return materielsService.uploadfilesService(files,m,id_souscategorie,id_locataire);
    }
/*
    @PostMapping("/{id_fournisseur}/{id_souscategorie}")
    public Materiels saveMateriels(@RequestParam("file") MultipartFile file, Materiels materiels,
                               @PathVariable Long id_souscategorie, @PathVariable Long id_fournisseur ) {
        return materielsService.saveMaterielsService(file,materiels,id_souscategorie,id_fournisseur);
    }    */

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource>getFileService(@PathVariable String filename){
        return materielsService.getFileService(filename);}
}


