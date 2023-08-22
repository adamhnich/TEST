package com.itgate.tunijobs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Collection;


@Entity
public class Location extends Reservation {

    private String description;
    private String commentaire;

    @OneToMany(mappedBy = "location", cascade = CascadeType.REMOVE)
    private Collection<Livraison> livraisons;

    @JsonIgnore
    public Collection<Livraison> getLivraisons() {
        return livraisons;
    }

    public void setLivraisons(Collection<Livraison> livraisons) {
        this.livraisons = livraisons;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
