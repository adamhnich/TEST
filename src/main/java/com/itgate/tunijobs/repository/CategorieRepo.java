package com.itgate.tunijobs.repository;

import com.itgate.tunijobs.models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepo extends JpaRepository<Categorie, Long> {
}
