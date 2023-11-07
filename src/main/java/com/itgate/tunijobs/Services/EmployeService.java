package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.Employe;
import com.itgate.tunijobs.models.Vendeur;
import com.itgate.tunijobs.repository.EmployeRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeService {

    @Autowired
    EmployeRepo employeRepo;

    @Autowired
    private EntityManager entityManager;

    public List<Employe> getall() {
        return employeRepo.findAll();
    }

    public Employe getOneEmployeService(Long id){
        return employeRepo.findById(id).orElse(null);
    }

    public Employe createEmployeService(Employe e) {
        return employeRepo.save(e);
    }

    public ResponseEntity deleteEmployeService(Long id) {
        employeRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Employe updateEmployeService(Employe e, Long id) {
        Employe e1 = employeRepo.findById(id).orElse(null);
        if (e1 != null) {
            e.setId(id);
            return employeRepo.saveAndFlush(e);
        } else {
            throw new RuntimeException("fail");
        }

    }



    public Long countEmployeProfiles() {
        String jpql = "SELECT COUNT(e) FROM Employe e";
        Query query = entityManager.createQuery(jpql);
        return (Long) query.getSingleResult();
    }
}
