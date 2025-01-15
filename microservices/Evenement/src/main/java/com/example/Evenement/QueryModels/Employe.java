package com.example.Evenement.QueryModels;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Employe {
    @Id
    private String employeId;
    private String nom;
    private String prenom;
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "employe_centres_interet", joinColumns = @JoinColumn(name = "employe_id"))
    @Column(name = "centre_interet")
    private List<String> centresInteret;


    public Employe(String employeId, String nom, String prenom, String email, List<String> centresInteret) {
        this.employeId = employeId;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.centresInteret = centresInteret;
    }

    public Employe() {

    }
}
