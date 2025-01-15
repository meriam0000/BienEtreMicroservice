package com.example.Evenement.EventHandlers;

import com.example.Evenement.Events.EmployeCreatedEvent;
import com.example.Evenement.Events.EventCreatedEvent;
import com.example.Evenement.QueryModels.Employe;
import com.example.Evenement.QueryModels.EvenementView;
import com.example.Evenement.Repositories.EmployeRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class EmployeEventHandler {
    private final EmployeRepository employeRepository;

    public EmployeEventHandler(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }
    @EventHandler
    public void on(EmployeCreatedEvent event) {
        Employe employe = new Employe(
                event.getEmployeId(),
                event.getNom(),
                event.getPrenom(),
                event.getEmail(),
                event.getCentresInteret()
        );
        employeRepository.save(employe);
    }
}
