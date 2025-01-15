package com.example.Evenement.Services;

import com.example.Evenement.commandes.AddEventToFavorisCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FavorisService {
    private final CommandGateway commandGateway;

    @Autowired
    public FavorisService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public void addEventToFavoris(String userId, String eventId) {
        String FavorisId = String.valueOf(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        commandGateway.send(new AddEventToFavorisCommand(FavorisId, eventId, userId));
    }

}
