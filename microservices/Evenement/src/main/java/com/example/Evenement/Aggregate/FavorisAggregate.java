package com.example.Evenement.Aggregate;

import com.example.Evenement.Events.EventAddedToFavorisEvent;
import com.example.Evenement.commandes.AddEventToFavorisCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.HashSet;
import java.util.Set;

@Aggregate
public class FavorisAggregate {

    @AggregateIdentifier
    private String favorisId;  // Unique identifier for Favoris
    private String userId;     // The user this favoris belongs to
    private Set<String> eventIds = new HashSet<>();  // List of event IDs the user has favorited

    public FavorisAggregate() {
        // Empty constructor for Axon
    }

    @CommandHandler
    public FavorisAggregate(AddEventToFavorisCommand command) {
        // Check if the event is already in the favoris list
        if (eventIds.contains(command.getEventId())) {
            throw new IllegalStateException("Event already added to favoris.");
        }

        // Apply the event to add the event to favoris
        AggregateLifecycle.apply(new EventAddedToFavorisEvent(command.getFavorisId(), command.getUserId(), command.getEventId()));
    }

    @EventSourcingHandler
    public void on(EventAddedToFavorisEvent event) {
        // Update the aggregate state when the event is applied
        this.userId = event.getUserId();
        this.eventIds.add(event.getEventId());
        this.favorisId = event.getFavorisId();
    }
}
