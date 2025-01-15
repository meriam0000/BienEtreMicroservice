package com.example.Evenement.Services;

import com.example.Evenement.commandes.CreateEventCommand;
import com.example.Evenement.commandes.JoinEventCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EventService {
    private final CommandGateway commandGateway;

    public EventService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public  void joinEvent(String eventId, String userId) {
       commandGateway.send(new JoinEventCommand(eventId, userId));
    }

    public void createEvent( String name, String description, LocalDateTime startDate, LocalDateTime endDate) {
        String eventId = String.valueOf(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE); // Generate unique ID

        CreateEventCommand command = new CreateEventCommand(eventId, name, description, startDate, endDate);
        commandGateway.send(command);
    }
}
