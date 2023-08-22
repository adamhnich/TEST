package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.Locataire;
import com.itgate.tunijobs.repository.LocataireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocataireService {

    @Autowired
    LocataireRepo locataireRepo;

    public List<Locataire> getall(){
        return locataireRepo.findAll() ;    }

    public  Locataire getOneLocataireService(Long id){
        return locataireRepo.findById(id).orElse(null);
    }

    public Locataire createLocataireService ( Locataire lt){
        return locataireRepo.save(lt);
    }

    public ResponseEntity deleteLocataireService (Long id) {
        locataireRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Locataire updateLocataireService ( Locataire lt,  Long id){
        Locataire lt1 = locataireRepo.findById(id).orElse(null);
        if (lt1 !=null){
            lt.setId(id);
            return locataireRepo.saveAndFlush(lt);
        }
        else{
            throw new RuntimeException("fail");
        }

    }


}
