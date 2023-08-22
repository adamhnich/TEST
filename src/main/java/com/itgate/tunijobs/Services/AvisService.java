package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.Avis;
import com.itgate.tunijobs.repository.AvisRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvisService {

    @Autowired
    AvisRepo avisRepo;

    public List<Avis> getall() {
        return avisRepo.findAll();
    }

    public Avis createAvisService(Avis a) {
        return avisRepo.save(a);
    }

    public ResponseEntity deleteAvisService(Long id) {
        avisRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Avis updateAvisService(Avis a, Long id) {
        Avis a1 = avisRepo.findById(id).orElse(null);
        if (a1 != null) {
            a.setId(id);
            return avisRepo.saveAndFlush(a);
        } else {
            throw new RuntimeException("fail");
        }

    }
}
