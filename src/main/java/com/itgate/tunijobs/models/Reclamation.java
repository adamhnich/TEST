package com.itgate.tunijobs.models;

import jakarta.persistence.*;

@Entity
public class Reclamation {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String description_Rec;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription_Rec() {
        return description_Rec;
    }

    public void setDescription_Rec(String description_Rec) {
        this.description_Rec = description_Rec;
    }
}
