package com.itgate.tunijobs.Services;


import com.itgate.tunijobs.models.Reclamation;
import com.itgate.tunijobs.models.User;
import com.itgate.tunijobs.repository.ReclamationRepo;
import com.itgate.tunijobs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReclamationService {

    @Autowired
    ReclamationRepo reclamationRepo;
    @Autowired
    UserRepository userRepo;

    public List<Reclamation> getall(){
        return reclamationRepo.findAll() ;    }

    public Reclamation creatReclamationService ( Reclamation r ,  Long id_user){
        User u = userRepo.findById(id_user).orElse(null);
        r.setUser(u);
        return reclamationRepo.save(r);
    }

    public ResponseEntity deleteReclamationService (Long id) {
        reclamationRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Reclamation updateReclamationService ( Reclamation r, Long id) {
        Reclamation r1 = reclamationRepo.findById(id).orElse(null);
        if (r1 != null) {
            r.setId(id);
            return reclamationRepo.saveAndFlush(r);
        } else {
            throw new RuntimeException("fail");
        }
    }

    public Reclamation getOneReclamationService(Long id){
        return reclamationRepo.findById(id).orElse(null);
    }



}
