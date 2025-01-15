package com.example.Evenement.commandes;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;
@Data
public class CreateEmployeCommand {
    @TargetAggregateIdentifier
    private String employeId;
    private String nom;
    private String prenom;
    private String email;
    private List<String> centresInteret;
    public CreateEmployeCommand(String employeId, String nom, String prenom, String email, List<String> centresInteret) {
        this.employeId = employeId;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.centresInteret = centresInteret;
    }
    public CreateEmployeCommand() {
    }

}
