package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.Client;
import com.itgate.tunijobs.models.Produit;
import com.itgate.tunijobs.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepo clientRepo;

    public List<Client> getall(){
        return clientRepo.findAll() ;    }

    public Client createClientService ( Client c){
        return clientRepo.save(c);
    }

    public Client getOneClient(Long id){
        return clientRepo.findById(id).orElse(null);
    }

    public ResponseEntity deleteClientService (Long id) {
        clientRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Client updateClientService ( Client c,  Long id){
        Client c1 = clientRepo.findById(id).orElse(null);
        if (c1 !=null){
            c.setId(id);
            return clientRepo.saveAndFlush(c);
        }
        else{
            throw new RuntimeException("fail");
        }

    }


}
