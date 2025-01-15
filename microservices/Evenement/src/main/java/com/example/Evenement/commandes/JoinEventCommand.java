package com.example.Evenement.commandes;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Data
public class JoinEventCommand {
    @TargetAggregateIdentifier

    private final String eventId;
    private final String userId;

    public JoinEventCommand(String eventId, String userId) {
        this.eventId = eventId;
        this.userId = userId;
    }


}
