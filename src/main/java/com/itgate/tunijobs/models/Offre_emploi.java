package com.itgate.tunijobs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Offre_emploi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String titre;
    private String description;
    private String budget;
    private String date_limite;
    private String etet_projet;
    private String date_creation;
    private String date_mise_a_jour;

    @ManyToOne
    @JoinColumn(name = "recruteur_id")
    private Recruteur recruteur;

    public Recruteur getRecruteur() {
        return recruteur;
    }

    public void setRecruteur(Recruteur recruteur) {
        this.recruteur = recruteur;
    }

    @OneToMany(mappedBy = "offre_emploi",cascade = CascadeType.REMOVE)
    private Collection<Profil> profils;
    @JsonIgnore
    public Collection<Profil> getProfils() {
        return profils;
    }

    public void setProfils(Collection<Profil> profils) {
        this.profils = profils;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getDate_limite() {
        return date_limite;
    }

    public void setDate_limite(String date_limite) {
        this.date_limite = date_limite;
    }

    public String getEtet_projet() {
        return etet_projet;
    }

    public void setEtet_projet(String etet_projet) {
        this.etet_projet = etet_projet;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getDate_mise_a_jour() {
        return date_mise_a_jour;
    }

    public void setDate_mise_a_jour(String date_mise_a_jour) {
        this.date_mise_a_jour = date_mise_a_jour;
    }
}
