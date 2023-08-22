package com.itgate.tunijobs.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUser ;
    private String  date_de_cration;
    private Float total;

    @OneToMany(mappedBy = "panier",cascade = CascadeType.REMOVE)
    private Collection<Produit> Produits;
    @JsonIgnore
    public Collection<Produit> getProduits() {
        return Produits;
    }

    public void setProduits(Collection<Produit> produits) {
        Produits = produits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getDate_de_cration() {
        return date_de_cration;
    }

    public void setDate_de_cration(String date_de_cration) {
        this.date_de_cration = date_de_cration;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
