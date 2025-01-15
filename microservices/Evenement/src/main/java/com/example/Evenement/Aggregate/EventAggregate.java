package com.example.Evenement.Aggregate;

import com.example.Evenement.Events.EventCreatedEvent;
import com.example.Evenement.Events.RatingAndCommentAddedEvent;
import com.example.Evenement.Events.UserJoinedEvent;
import com.example.Evenement.QueryModels.RatingAndComment;
import com.example.Evenement.commandes.AddRatingAndCommentCommand;
import com.example.Evenement.commandes.CreateEventCommand;
import com.example.Evenement.commandes.JoinEventCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Aggregate
public class EventAggregate {

    @AggregateIdentifier
    private String eventId;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Set<String> participants = new HashSet<>();
    private Set<RatingAndComment> ratingsAndComments = new HashSet<>();




    public EventAggregate() {
        // Empty constructor needed for Axon
    }

    @CommandHandler
    public EventAggregate(CreateEventCommand command) {
        AggregateLifecycle.apply(new EventCreatedEvent(
                command.getEventId(),
                command.getName(),
                command.getDescription(),
                command.getStartDate(),
                command.getEndDate()
        ));
    }

    @EventSourcingHandler
    public void on(EventCreatedEvent event) {
        this.eventId = event.getEventId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
    }
    @CommandHandler
    public void handle(JoinEventCommand command) {
        if (participants.contains(command.getUserId())) {
            throw new IllegalStateException("User already joined the event.");
        }
        AggregateLifecycle.apply(new UserJoinedEvent(command.getEventId(), command.getUserId()));
    }

    @EventSourcingHandler
    public void on(UserJoinedEvent event) {
        this.eventId = event.getEventId();
        this.participants.add(event.getUserId());
    }


    @CommandHandler
    public EventAggregate(AddRatingAndCommentCommand command) {

        if (command.getRating() < 1 || command.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        // Check if the user has already rated or commented
        if (hasUserRated(command.getUserId())) {
            throw new IllegalStateException("User has already rated this event");
        }


        // Publish the event that the rating and comment were added
        AggregateLifecycle.apply(new RatingAndCommentAddedEvent(
                command.getEventId(), command.getUserId(), command.getRating(), command.getComment()
        ));
    }

    private boolean hasUserRated(String userId) {
        // Check if the user has already rated this event
        return ratingsAndComments.stream()
                .anyMatch(rating -> rating.getUser().getUserId().equals(userId));
    }


    @EventSourcingHandler
    public void on(RatingAndCommentAddedEvent event) {
        this.eventId = event.getEventId();
        System.out.println("RatingAndCommentAddedEvent: " + event.getEventId());
        RatingAndComment ratingAndComment = new RatingAndComment(
                event.getUserId(), event.getRating(), event.getComment()
        );
        this.ratingsAndComments.add(ratingAndComment);
    }


}

