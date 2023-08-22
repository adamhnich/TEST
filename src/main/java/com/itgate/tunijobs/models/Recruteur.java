package com.itgate.tunijobs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Recruteur extends User {


    private String telephonerec;
    private String adresse;
    private String projet;

    public Recruteur(String username, String email, String password, String telephonerec, String adresse, String projet) {
        super(username, email, password);
        this.telephonerec = telephonerec;
        this.adresse = adresse;
        this.projet = projet;
    }

    @OneToMany(mappedBy = "recruteur",cascade = CascadeType.REMOVE)
    private Collection<Avis> avis;

    @OneToMany(mappedBy = "recruteur",cascade = CascadeType.REMOVE)
    private Collection<Offre_emploi> offre_emplois;
    @JsonIgnore
    public Collection<Offre_emploi> getOffre_emplois() {
        return offre_emplois;
    }

    public void setOffre_emplois(Collection<Offre_emploi> offre_emplois) {
        this.offre_emplois = offre_emplois;
    }

    public Recruteur() {

    }
   // @OneToMany(mappedBy = "recruteur",cascade = CascadeType.REMOVE)
   // private Collection<Like> likes;

    @OneToMany(mappedBy = "recruteur",cascade = CascadeType.REMOVE)
    private Collection<LikeProfil> likeProfils;
@JsonIgnore
    public Collection<LikeProfil> getLikeProfils() {
        return likeProfils;
    }

    public void setLikeProfils(Collection<LikeProfil> likeProfils) {
        this.likeProfils = likeProfils;
    }

    @JsonIgnore
    public Collection<Avis> getAvis() {
        return avis;
    }

    public void setAvis(Collection<Avis> avis) {
        this.avis = avis;
    }


    public String getTelephonerec() {
        return telephonerec;
    }

    public void setTelephonerec(String telephonerec) {
        this.telephonerec = telephonerec;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getProjet() {
        return projet;
    }

    public void setProjet(String projet) {
        this.projet = projet;
    }
}
