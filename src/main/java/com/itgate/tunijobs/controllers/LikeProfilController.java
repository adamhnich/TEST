package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.Services.LikeProfilService;
import com.itgate.tunijobs.models.Commande;
import com.itgate.tunijobs.models.LikeProfil;
import com.itgate.tunijobs.repository.LikeProfilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/LikeProfil")
@CrossOrigin("*")
public class LikeProfilController {
    @Autowired
    LikeProfilService likeProfilService;
/*
    @PostMapping("/add_like/{idr}/{idp}")
    @ResponseBody
    public LikeProfil addLikeProfil(@PathVariable("idr")  long idrecruteur , @PathVariable("idp")  long idprofil) {
        return likeProfilService.addLikeProfil(idprofil,idrecruteur);

    } */

    @PostMapping("/{idrecruteur}/{idprofil}")
    public LikeProfil addLikeProfil(@PathVariable Long idrecruteur, @PathVariable Long idprofil) {
        return likeProfilService.addLikeProfil(idrecruteur, idprofil);
    }
/*
    @DeleteMapping("/remove_like/{profil-id}/{recruteur-id}")
    @ResponseBody
    public void DeleteLikeProfil(@PathVariable("profil-id")  long profilid,@PathVariable("recruteur-id")  long idrecruteur) {
        likeProfilService.DeleteLikeProfil(profilid ,idrecruteur);

    }    */

    @DeleteMapping("/{id}")
    public String delleteLike(@PathVariable Long id) {
        return  likeProfilService.removeLike(id);
    }
}
