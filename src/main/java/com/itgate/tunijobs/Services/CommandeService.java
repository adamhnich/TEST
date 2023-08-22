package com.itgate.tunijobs.Services;

import com.itgate.tunijobs.models.Client;
import com.itgate.tunijobs.models.Commande;
import com.itgate.tunijobs.repository.ClientRepo;
import com.itgate.tunijobs.repository.CommandeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class CommandeService {

    @Autowired
    CommandeRepo commandeRepo;

    @Autowired
    ClientRepo clientRepo;

    public List<Commande> getall(){
        return commandeRepo.findAll() ;    }

    public Commande getOneCommande(Long id){
        return commandeRepo.findById(id).orElse(null);
    }


    public Commande createCommandeService ( Commande c){
        return commandeRepo.save(c);
    }

    public ResponseEntity deleteCommandeService (Long id) {
        commandeRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Commande updateCommandeService ( Commande c, Long id){
        Commande c1 = commandeRepo.findById(id).orElse(null);
        if (c1 !=null){
            c.setId(id);
            return commandeRepo.saveAndFlush(c);
        }
        else{
            throw new RuntimeException("fail");
        }

    }


        public Commande attribuerCommandeAuClient(Long commandeId, Long clientId) {
            Commande commande = commandeRepo.findById(commandeId)
                    .orElseThrow(() -> new NotFoundException("Commande introuvable"));

            Client client = clientRepo.findById(clientId).get();


            commande.setClient(client);
            return commandeRepo.save(commande);
        }

    public List<Commande> getCommandesByIdClient(Long clientId) {
        return commandeRepo.findByClientId(clientId);
    }

        // Autres méthodes de service si nécessaire



}
