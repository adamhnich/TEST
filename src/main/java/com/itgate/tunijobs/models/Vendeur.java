package com.itgate.tunijobs.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Collection;

@Entity
public class Vendeur extends User{

    private String soldeVendeur;
    private String nomboutique;
    private String matriculefiscale;

    public Vendeur(String username, String email, String password, String soldeVendeur, String nomboutique, String matriculefiscale) {
        super(username, email, password);
        this.soldeVendeur = soldeVendeur;
        this.nomboutique = nomboutique;
        this.matriculefiscale = matriculefiscale;
    }

    public Vendeur() {
    }

    @OneToMany(mappedBy = "vendeur",cascade = CascadeType.REMOVE)
    private Collection<Produit> produits;
@JsonIgnore
    public Collection<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }

    public String getSoldeVendeur() {
        return soldeVendeur;
    }

    public void setSoldeVendeur(String soldeVendeur) {
        this.soldeVendeur = soldeVendeur;
    }

    public String getNomboutique() {
        return nomboutique;
    }

    public void setNomboutique(String nomboutique) {
        this.nomboutique = nomboutique;
    }

    public String getMatriculefiscale() {
        return matriculefiscale;
    }

    public void setMatriculefiscale(String matriculefiscale) {
        this.matriculefiscale = matriculefiscale;
    }
}
