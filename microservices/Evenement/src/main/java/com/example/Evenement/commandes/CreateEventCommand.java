package com.example.Evenement.commandes;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Data
public class CreateEventCommand {
    @TargetAggregateIdentifier

    private final String eventId;
    private final String name;
    private final String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    public CreateEventCommand(String eventId, String name, String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.eventId = eventId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
