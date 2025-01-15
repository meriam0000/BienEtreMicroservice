package com.example.Evenement.Events;

import lombok.Data;

import java.util.List;
@Data
public class EmployeCreatedEvent {
    private String employeId;
    private String nom;
    private String prenom;
    private String email;
    private List<String> centresInteret;
    public EmployeCreatedEvent(String employeId, String nom, String prenom, String email, List<String> centresInteret) {
        this.employeId = employeId;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.centresInteret = centresInteret;
    }
}
