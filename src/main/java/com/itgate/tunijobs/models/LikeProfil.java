package com.itgate.tunijobs.models;

import jakarta.persistence.*;

@Entity
@Table(name = "likeProfil")
public class LikeProfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idlikee;

    @ManyToOne
    @JoinColumn(name = "Recruteur_id")
    private Recruteur recruteur;

    @ManyToOne
    @JoinColumn(name = "Profil_id")
    private Profil profil;

    public Long getIdlikee() {
        return idlikee;
    }

    public void setIdlikee(Long idlikee) {
        this.idlikee = idlikee;
    }

    public Recruteur getRecruteur() {
        return recruteur;
    }

    public void setRecruteur(Recruteur recruteur) {
        this.recruteur = recruteur;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }


}
