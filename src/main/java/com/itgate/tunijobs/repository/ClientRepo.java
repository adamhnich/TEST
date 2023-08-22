package com.itgate.tunijobs.repository;


import com.itgate.tunijobs.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepo extends JpaRepository<Client, Long> {
}
