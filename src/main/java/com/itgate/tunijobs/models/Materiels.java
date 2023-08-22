package com.itgate.tunijobs.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity

public class Materiels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long price;
    private Long TVA;
    private Long compter_en_stock;
    private String nom;
    private String image;
    private String marque;
    private String promotion;
    private String reference;
    private String description;
    private String date_de_fabrication;
    private ArrayList<String> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "locataire_id")
    private Locataire locataire;

    public Locataire getLocataire() {
        return locataire;
    }

    public void setLocataire(Locataire locataire) {
        this.locataire = locataire;
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

    @ManyToMany
    @JoinTable(name = "reservation_materiels", joinColumns = @JoinColumn(name = "materiels_id"),inverseJoinColumns = @JoinColumn(name ="reservation_id"))
    private Collection<Reservation> reservations;
    @JsonIgnore
    public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public void setImage(ArrayList<String> fileNames) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getTVA() {
        return TVA;
    }

    public void setTVA(Long TVA) {
        this.TVA = TVA;
    }

    public Long getCompter_en_stock() {
        return compter_en_stock;
    }

    public void setCompter_en_stock(Long compter_en_stock) {
        this.compter_en_stock = compter_en_stock;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_de_fabrication() {
        return date_de_fabrication;
    }

    public void setDate_de_fabrication(String date_de_fabrication) {
        this.date_de_fabrication = date_de_fabrication;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
