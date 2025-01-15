package com.example.Evenement.commandes;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class AddEventToFavorisCommand {
    @TargetAggregateIdentifier
    private String favorisId;   // Add favorisId to the command
    private final String eventId;
    private final String userId;

    public AddEventToFavorisCommand(String favorisId,String eventId, String userId) {
        this.eventId = eventId;
        this.userId = userId;
        this.favorisId = favorisId;
    }


}
