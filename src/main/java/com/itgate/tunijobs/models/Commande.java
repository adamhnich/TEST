package com.itgate.tunijobs.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long qtetotal;
    private String date_cmd;
    private String description_cmd ;
    private String duree_cmd;
    private String etat_cmd;


    @ManyToOne
    @JoinColumn(name = "Client_id")
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToMany
    @JsonIgnoreProperties("commandes")
    @JoinTable(name = "Commande_produit", joinColumns = @JoinColumn(name = "produit_id"),inverseJoinColumns = @JoinColumn(name ="commande_id"))
    private Collection<Produit> produits;
   // @JsonIgnore
    public Collection<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }

    /*
        @ManyToMany(mappedBy = "commandes",cascade = CascadeType.REMOVE)
        private Collection<Produit> produits;
        @JsonIgnore
        public Collection<Produit> getProduits() {
            return produits;
        }

        public void setProduits(Collection<Produit> produits) {
            this.produits = produits;
        }
    */
    @OneToMany(mappedBy = "commande",cascade = CascadeType.REMOVE)
    private Collection<Livraison> Livraisons;

@JsonIgnore
    public Collection<Livraison> getLivraisons() {
        return Livraisons;
    }

    public void setLivraisons(Collection<Livraison> livraisons) {
        Livraisons = livraisons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQtetotal() {
        return qtetotal;
    }

    public void setQtetotal(Long qtetotal) {
        this.qtetotal = qtetotal;
    }

    public String getDate_cmd() {
        return date_cmd;
    }

    public void setDate_cmd(String date_cmd) {
        this.date_cmd = date_cmd;
    }

    public String getDescription_cmd() {
        return description_cmd;
    }

    public void setDescription_cmd(String description_cmd) {
        this.description_cmd = description_cmd;
    }

    public String getDuree_cmd() {
        return duree_cmd;
    }

    public void setDuree_cmd(String duree_cmd) {
        this.duree_cmd = duree_cmd;
    }

    public String getEtat_cmd() {
        return etat_cmd;
    }

    public void setEtat_cmd(String etat_cmd) {
        this.etat_cmd = etat_cmd;
    }

    public void addproduct(Produit p){
        if(produits == null){
            produits = new ArrayList<>();
        }
        produits.add(p);
    }
}
