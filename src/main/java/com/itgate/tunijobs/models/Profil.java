package com.itgate.tunijobs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Profil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private String date_creation;
    private String date_mise_a_jour;
    private String prix_heurs;

    private int LikesNbr=0;

    @Enumerated(EnumType.STRING)
    private Popularite popularite =Popularite.notfamous;

    public int getLikesNbr() {
        return LikesNbr;
    }

    public void setLikesNbr(int likesNbr) {
        LikesNbr = likesNbr;
    }

    public Popularite getPopularite() {
        return popularite;
    }

    public void setPopularite(Popularite popularite) {
        this.popularite = popularite;
    }

    @ManyToOne
    @JoinColumn(name = "Souscategorie_id")
    private Souscategorie souscategorie;

    public Souscategorie getSouscategorie() {
        return souscategorie;
    }

    public void setSouscategorie(Souscategorie souscategorie) {
        this.souscategorie = souscategorie;
    }

   // @OneToMany(mappedBy = "profil",cascade = CascadeType.REMOVE)
  //  private Collection<Like> likes;

    @OneToMany(mappedBy = "profil",cascade = CascadeType.REMOVE)
    private Collection<LikeProfil> likeProfils;
@JsonIgnore
    public Collection<LikeProfil> getLikeProfils() {
        return likeProfils;
    }

    public void setLikeProfils(Collection<LikeProfil> likeProfils) {
        this.likeProfils = likeProfils;
    }

    @ManyToOne
    @JoinColumn(name = "Offre_emploi_id")
    private Offre_emploi offre_emploi;

    public Offre_emploi getOffre_emploi() {
        return offre_emploi;
    }

    public void setOffre_emploi(Offre_emploi offre_emploi) {
        this.offre_emploi = offre_emploi;
    }

    @ManyToOne
    @JoinColumn(name = "Employe_id")
    private Employe employe;

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
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

    public String getPrix_heurs() {
        return prix_heurs;
    }

    public void setPrix_heurs(String prix_heurs) {
        this.prix_heurs = prix_heurs;
    }
}
