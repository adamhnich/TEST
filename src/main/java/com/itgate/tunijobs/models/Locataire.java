package com.itgate.tunijobs.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Collection;

@Entity
public class Locataire extends User{



    private String soldeLocataire;
    private String nomboutique;
    private String matriculefiscale;

    @OneToMany(mappedBy = "locataire",cascade = CascadeType.REMOVE)
    private Collection<Materiels> materiels;
    @JsonIgnore
    public Collection<Materiels> getMateriels() {
        return materiels;
    }

    public void setMateriels(Collection<Materiels> materiels) {
        this.materiels = materiels;
    }


    public String getSoldeLocataire() {
        return soldeLocataire;
    }

    public void setSoldeLocataire(String soldeLocataire) {
        this.soldeLocataire = soldeLocataire;
    }

    public String getNomboutique() {
        return nomboutique;
    }

    public void setNomboutique(String nomboutique) {
        this.nomboutique = nomboutique;
    }

    public String getMatriculefiscale() {
        return matriculefiscale;
    }

    public void setMatriculefiscale(String matriculefiscale) {
        this.matriculefiscale = matriculefiscale;
    }
}
