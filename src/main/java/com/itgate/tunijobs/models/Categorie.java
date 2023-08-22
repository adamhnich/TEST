package com.itgate.tunijobs.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom_Cat;
    private String description;



    @OneToMany(mappedBy = "categorie",cascade = CascadeType.REMOVE)
    private Collection<Souscategorie> Souscategories;

    @JsonIgnore
    public Collection<Souscategorie> getSouscategories() {
        return Souscategories;
    }

    public void setSouscategories(Collection<Souscategorie> souscategories) {
        Souscategories = souscategories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom_Cat() {
        return nom_Cat;
    }

    public void setNom_Cat(String nom_Cat) {
        this.nom_Cat = nom_Cat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
