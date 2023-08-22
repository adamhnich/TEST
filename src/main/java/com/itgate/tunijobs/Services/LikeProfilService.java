package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.LikeProfil;
import com.itgate.tunijobs.models.Popularite;
import com.itgate.tunijobs.models.Profil;
import com.itgate.tunijobs.models.Recruteur;
import com.itgate.tunijobs.repository.LikeProfilRepo;
import com.itgate.tunijobs.repository.ProfilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeProfilService {

    @Autowired
    ProfilService profilService;
    @Autowired
    RecruteurService recruteurService ;
    @Autowired
    LikeProfilRepo likeProfilRepo;
    @Autowired
    ProfilRepo profilRepo;


    public LikeProfil addLikeProfil(Long idr, Long idp) {
        LikeProfil l = new LikeProfil();
        Profil p = profilService.getOneProfilService(idp);
        Recruteur r = recruteurService.getOneRecruteurService(idr);

        l.setRecruteur(r);
        l.setProfil(p);
        p.setLikesNbr(p.getLikesNbr() + 1);

        if (p.getLikesNbr() >= 3 && p.getLikesNbr() <= 5) {
            p.setPopularite(Popularite.pouplar);
        } else if (p.getLikesNbr() >= 6 && p.getLikesNbr() <= 10) {
            p.setPopularite(Popularite.superFamous);
        } else if (p.getLikesNbr() >= 11 && p.getLikesNbr() <= 15) {
            p.setPopularite(Popularite.famous);
        }

        profilRepo.save(p);
        return likeProfilRepo.save(l);
    }

/*
    public LikeProfil addLikeProfil(long idp, long idr) {

        LikeProfil l = new LikeProfil();
        Profil p = profilService.getOneProfilService(idp);
        Recruteur r = recruteurService.getOneRecruteurService(idr);

          l.setRecruteur(r);
          l.setProfil(p);
        p.setLikesNbr(p.getLikesNbr()+1);
        profilService.createProfilService(p);

        return likeProfilRepo.save(l);
    }    */

/*
    public String DeleteLikeProfil(long idp, long idr) {

        Profil p = profilService.getOneProfilService(idp);
        Recruteur r = recruteurService.getOneRecruteurService(idr);
       // LikeProfil l = likeProfilRepo.findLikeByIdProfilAndIdRecruteur(p,r);

        //likeProfilRepo.delete(l);
        return "Dislike";
    }  */

    public String removeLike(Long id) {
        LikeProfil p = likeProfilRepo.findById(id).orElse(null);
        Profil pro=p.getProfil();
        if (pro.getLikesNbr() > 0) {
            pro.setLikesNbr(pro.getLikesNbr() - 1);

            if (pro.getLikesNbr() < 2) {
                pro.setPopularite(Popularite.notfamous);
            } else if (pro.getLikesNbr() >= 3 && pro.getLikesNbr() <= 5) {
                pro.setPopularite(Popularite.pouplar);
            } else if (pro.getLikesNbr() >= 6 && pro.getLikesNbr() <= 10) {
                pro.setPopularite(Popularite.superFamous);
            } else if (pro.getLikesNbr() >= 11 && pro.getLikesNbr() <= 15) {
                pro.setPopularite(Popularite.famous);
            }

            likeProfilRepo.save(p);

            return "disliked";
        } else {
            return "cannot dislike further, likes count is already at 0";
        }
    }
}
