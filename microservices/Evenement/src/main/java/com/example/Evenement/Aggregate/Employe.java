package com.example.Evenement.Aggregate;

import com.example.Evenement.Events.EmployeCreatedEvent;
import com.example.Evenement.commandes.CreateEmployeCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;
@Aggregate
public class Employe {
    @AggregateIdentifier
    private String employeId;
    private String nom;
    private String prenom;
    private String email;
    private List<String> centresInteret;

    public Employe() {
        // Required by Axon
    }

    @CommandHandler
    public Employe(CreateEmployeCommand command) {
        // Ensure that business rules are applied (e.g., interests can't be empty)
        if (command.getCentresInteret().isEmpty()) {
            throw new IllegalArgumentException("Centres of interest cannot be empty.");
        }
        AggregateLifecycle.apply(new EmployeCreatedEvent(command.getEmployeId(),command.getNom() ,command.getPrenom(),command.getEmail(),command.getCentresInteret()));
    }

    @EventSourcingHandler
    public void on(EmployeCreatedEvent event) {
        this.employeId = event.getEmployeId();
        this.nom = event.getNom();
        this.prenom = event.getPrenom();
        this.email = event.getEmail();
        this.centresInteret = event.getCentresInteret();
    }
}
