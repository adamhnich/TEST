package com.itgate.tunijobs.repository;


import com.itgate.tunijobs.models.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepo extends JpaRepository<Employe, Long> {
}
