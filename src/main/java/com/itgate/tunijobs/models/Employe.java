package com.itgate.tunijobs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;


@Entity
public class Employe extends User {

    private String nom;

    private String telephone;
    private String adresse;

    public Employe(String username, String email, String password, String nom, String telephone, String adresse) {
        super(username, email, password);
        this.nom = nom;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    @OneToMany(mappedBy = "employe",cascade = CascadeType.REMOVE)
    private Collection<Profil> profils;

    public Employe() {

    }

    @JsonIgnore
    public Collection<Profil> getProfils() {
        return profils;
    }

    public void setProfils(Collection<Profil> profils) {
        this.profils = profils;
    }



    @OneToMany(mappedBy = "employe",cascade = CascadeType.REMOVE)
    private Collection<Avis> avis;
    @JsonIgnore
    public Collection<Avis> getAvis() {
        return avis;
    }

    public void setAvis(Collection<Avis> avis) {
        this.avis = avis;
    }



    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
