package com.itgate.tunijobs.controllers;

import com.itgate.tunijobs.Services.ClientService;
import com.itgate.tunijobs.Services.ProduitService;
import com.itgate.tunijobs.models.Client;
import com.itgate.tunijobs.models.Commande;
import com.itgate.tunijobs.Services.CommandeService;
import com.itgate.tunijobs.models.Produit;
import com.itgate.tunijobs.repository.CommandeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Commande")
@CrossOrigin("*")
public class CommandeController {

    @Autowired
    CommandeService commandeService;
    @Autowired
    ProduitService produitService;
    @Autowired
    ClientService clientService;

    @Autowired
    CommandeRepo commandeRepo;


    @GetMapping
    public List<Commande> getall(){
        return commandeService.getall() ;
    }

    @GetMapping ("/getone/{id}")
    public Commande getOneCommande(@PathVariable Long id){
        return commandeRepo.findById(id).orElse(null);
    }


    @PostMapping("/{id}")
    public Commande createCommande (@RequestBody Commande c, @PathVariable Long id){
        Client client=clientService.getOneClient(id);
        c.setClient(client);
        return commandeService.createCommandeService(c);
    }

    @PostMapping("/savee/{id_client}")

    public Commande savee(@RequestBody Commande o, @PathVariable Long id_client, @RequestParam List<Long>ids){

        for (int i=0; i<ids.size();i++){
            Produit p =produitService.getOneProduitService(ids.get(i));
            o.addproduct(p);
            //System.out.println(p);
        }
        Client c =clientService.getOneClient(id_client);
        o.setClient(c);
        return  commandeService.createCommandeService(o);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCommande (@PathVariable Long id) {
        return commandeService.deleteCommandeService(id);

    }
    @PutMapping("/{id}")
    public Commande updateCommande (@RequestBody Commande c, @PathVariable Long id){
        return commandeService.updateCommandeService(c,id);

    }
    @PutMapping("/{commandeId}/{clientId}")
    public Commande attribuerCommandeAuClient(@PathVariable Long commandeId, @PathVariable Long clientId) {
        return commandeService.attribuerCommandeAuClient(commandeId, clientId);
    }
}
