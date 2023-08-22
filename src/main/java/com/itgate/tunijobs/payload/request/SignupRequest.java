package com.itgate.tunijobs.payload.request;

import java.util.Set;

import jakarta.validation.constraints.*;

public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  private String adress;
  private String city;
  private String nom;
  private String telephone;
  private String image;
  private String telephonerec;
  private String adresse;
  private String projet;

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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  private String service;
  private String company;
  private String matricule;

  private String soldeVendeur;
  private String nomboutique;
  private String matriculefiscale;

  public String getSoldeVendeur() {
    return soldeVendeur;
  }

  public void setSoldeVendeur(String soldeVendeur) {
    this.soldeVendeur = soldeVendeur;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getMatricule() {
    return matricule;
  }

  public void setMatricule(String matricule) {
    this.matricule = matricule;
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
}
