package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.models.Commande;
import com.itgate.tunijobs.models.Produit;

import com.itgate.tunijobs.Services.ProduitService;
import com.itgate.tunijobs.models.ResponseMessage;
import com.itgate.tunijobs.utils.StorageService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Produits")
@CrossOrigin("*")
public class ProduitController {

    @Autowired
    ProduitService produitService;

    @Autowired
    StorageService storageService;

    @GetMapping
    public List<Produit> getall(){
        return produitService.getall() ;
    }

    @GetMapping("/{id}")
    public Produit getOneProduit(@PathVariable Long id){
        return produitService.getOneProduitService(id);
    }

    @PutMapping("/{produitId}/{commandeId}")
    public Produit attribuerProduitAuCommande(@PathVariable Long produitId, @PathVariable Long commandeId) {
        return produitService.attribuerProduitAuCommande(produitId, commandeId);
    }


    @PostMapping("/addproduit")
        public Produit createProduit (Produit p, @RequestParam("file")MultipartFile file){
        String img=storageService.store(file);
        p.setImage(img);
        return produitService.createProduitService(p);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFiles(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;" +
                        " filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduit (@PathVariable Long id) {
        return  produitService.deleteProduitService(id);

    }
    @PutMapping("/{id}")
    public Produit updateProduit (@RequestBody Produit p, @PathVariable Long id){
        return produitService.updateProduitService(p,id);
    }

    @PostMapping("/upload/{id_vendeur}/{id_souscategorie}")
    public ResponseEntity<ResponseMessage> uploadfilesService(@RequestParam("files") MultipartFile[] files, Produit p,
                                                              @PathVariable Long id_souscategorie, @PathVariable Long id_vendeur ) {
        return produitService.uploadfilesService(files,p,id_souscategorie,id_vendeur);
    }

    @PostMapping("/{id_vendeur}/{id_souscategorie}")
    public Produit saveProduit(@RequestParam("file") MultipartFile file, Produit produit,
                               @PathVariable Long id_souscategorie, @PathVariable Long id_vendeur ) {
        return produitService.saveProduitService(file,produit,id_souscategorie,id_vendeur);
    }







}

