package com.itgate.tunijobs.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)

    private Long id;
    private Long qtetotal;
    private String date_reservation;
    private String description_resvation ;
    private String duree_reservation;
    private String etat_reservation;


    @ManyToOne
    @JoinColumn(name = "Client_id")
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToMany
    @JoinTable(name = "reservation_materiels", joinColumns = @JoinColumn(name = "reservation_id"),inverseJoinColumns = @JoinColumn(name ="materiels_id"))
    private Collection<Materiels> materiels;
    @JsonIgnore
    public Collection<Materiels> getMateriels() {
        return materiels;
    }

    public void setMateriels(Collection<Materiels> materiels) {
        this.materiels = materiels;
    }

    /*
    @ManyToMany(mappedBy = "reservation",cascade = CascadeType.REMOVE)
    private Collection<Materiels> materiels;
    @JsonIgnore
    public Collection<Materiels> getMateriels() {
        return materiels;
    }

    public void setMateriels(Collection<Materiels> materiels) {
        this.materiels = materiels;
    }   */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQtetotal() {
        return qtetotal;
    }

    public void setQtetotal(Long qtetotal) {
        this.qtetotal = qtetotal;
    }

    public String getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(String date_reservation) {
        this.date_reservation = date_reservation;
    }

    public String getDescription_resvation() {
        return description_resvation;
    }

    public void setDescription_resvation(String description_resvation) {
        this.description_resvation = description_resvation;
    }

    public String getDuree_reservation() {
        return duree_reservation;
    }

    public void setDuree_reservation(String duree_reservation) {
        this.duree_reservation = duree_reservation;
    }

    public String getEtat_reservation() {
        return etat_reservation;
    }

    public void setEtat_reservation(String etat_reservation) {
        this.etat_reservation = etat_reservation;
    }
}
