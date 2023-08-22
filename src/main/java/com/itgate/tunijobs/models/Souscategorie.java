package com.itgate.tunijobs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Souscategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;


    @OneToMany(mappedBy = "souscategorie",cascade = CascadeType.REMOVE)
    private Collection<Produit> Produits;

    @JsonIgnore
    public Collection<Produit> getProduits() {
        return Produits;
    }

    public void setProduits(Collection<Produit> produits) {
        Produits = produits;
    }

    @OneToMany(mappedBy = "souscategorie",cascade = CascadeType.REMOVE)
    private Collection<Materiels> materiels;
    @JsonIgnore
    public Collection<Materiels> getMateriels() {
        return materiels;
    }

    public void setMateriels(Collection<Materiels> materiels) {
        this.materiels = materiels;
    }

    @OneToMany(mappedBy = "souscategorie",cascade = CascadeType.REMOVE)
    private Collection<Profil> profils;
    @JsonIgnore
    public Collection<Profil> getProfils() {
        return profils;
    }

    public void setProfils(Collection<Profil> profils) {
        this.profils = profils;
    }

    @ManyToOne
    @JoinColumn(name = "Categorie_id")
    private Categorie categorie;

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
