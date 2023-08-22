package com.itgate.tunijobs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name="client")
public class Client extends User {


    private String adress;
    private String city;
    private String image;




    public Client(String username, String email, String password, String adress, String city, String image) {
        super(username, email, password);
        this.adress = adress;
        this.city = city;
        this.image = image;
    }

    public  Client(){

   }
    @OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE)
    private Collection<Commande> commandes;
    @JsonIgnore
    public Collection<Commande> getCommandes() {
        return commandes;
    }
    public void setCommandes(Collection<Commande> commandes) {
        this.commandes = commandes;
    }

    public String getAdress() {
        return adress;
    }



    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
