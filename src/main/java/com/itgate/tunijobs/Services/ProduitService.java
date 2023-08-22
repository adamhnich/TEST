package com.itgate.tunijobs.Services;


import com.itgate.tunijobs.models.*;
import com.itgate.tunijobs.repository.CommandeRepo;
import com.itgate.tunijobs.repository.ProduitRepo;
import com.itgate.tunijobs.repository.SouscategorieRepo;
import com.itgate.tunijobs.repository.VendeurRepo;
import com.itgate.tunijobs.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ProduitService {
    @Autowired
    ProduitRepo produitRepo;
    @Autowired
    CommandeRepo commandeRepo;
    @Autowired
    VendeurRepo vendeurRepo;
    @Autowired
    SouscategorieRepo souscategorieRepo;
    @Autowired
    private StorageService storage;


    private final Path rootLocation = Paths.get("upload");

    public List<Produit> getall(){
        return produitRepo.findAll() ;
    }

    public Produit getOneProduitService( Long id){
        return produitRepo.findById(id).orElse(null);
    }


    public Produit createProduitService (Produit p){
        return produitRepo.save(p);
    }


    public ResponseEntity deleteProduitService (Long id) {
        produitRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }


    public Produit attribuerProduitAuCommande(Long produitId, Long commandeId) {
        Produit produit = produitRepo.findById(produitId)
                .orElseThrow(() -> new NotFoundException("Produit introuvable"));

        Commande commande = commandeRepo.findById(commandeId).get();



        commande.getProduits().add(produit);
        return produitRepo.save(produit);
    }


    public List<Produit> getProduitsByIdVendeur(Long vendeurId) {
        return produitRepo.findByVendeurId(vendeurId);
    }

    public Produit updateProduitService ( Produit p, Long id){
        Produit p1 = produitRepo.findById(id).orElse(null);
        if (p1 !=null){
            p.setId(id);
            return produitRepo.saveAndFlush(p);
        }
        else{
            throw new RuntimeException("fail");
        }

    }

    public ResponseEntity<ResponseMessage> uploadfilesService(MultipartFile[] files, Produit p, Long id_souscategorie, Long id_vendeur) {
        String message = "";
        try {
            ArrayList<String> fileNames = new ArrayList<>();
            Arrays.asList(files).stream().forEach(file -> {
                try {
                    String fileName = Integer.toString(new Random().nextInt(1000000));
                    String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'),
                            file.getOriginalFilename().length());
                    String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
                    String original = name + fileName + ext;
                    Files.copy(file.getInputStream(), this.rootLocation.resolve(original));
                    fileNames.add(original);
                    p.setImage(fileNames);
                } catch (Exception e) {
                    throw new RuntimeException("fail file problem backend");
                }
            });

            Vendeur vd = vendeurRepo.findById(id_vendeur).orElse(null);
            p.setVendeur(vd);
            Souscategorie sub = souscategorieRepo.findById(id_souscategorie).orElse(null);
            p.setSouscategorie(sub);

            produitRepo.save(p);
            message = "Uploaded the file successufully" + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        } catch (Exception e) {
            message = "fail to upload";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }





    public Produit saveProduitService(MultipartFile file,Produit produit, Long id_souscategorie, Long id_vendeur) {
        try {
            String fileName = Integer.toString(new Random().nextInt(1000000));
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'),
                    file.getOriginalFilename().length());
            String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
            String original = name + fileName + ext;
            Files.copy(file.getInputStream(), this.rootLocation.resolve(original));
            Vendeur vd = vendeurRepo.findById(id_vendeur).orElse(null);
            produit.setVendeur(vd);
            Souscategorie sub = souscategorieRepo.findById(id_souscategorie).orElse(null);
            produit.setSouscategorie(sub);
            produit.setImage(original);
            return produitRepo.save(produit);

        } catch (Exception e) {
            throw new RuntimeException("fail file problem backend");
        }
    }




    public ResponseEntity<Resource>getFileService(String filename){
        Resource file=storage.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachement;filename=\""+file.getFilename()+"\"")
                .body(file);
    }



  //  public UploadImageProduit saveImage(UploadImageProduit p) {return uploadImage.save(p);}

}
