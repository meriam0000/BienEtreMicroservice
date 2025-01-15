package com.example.Evenement.EventHandlers;

import com.example.Evenement.Events.EventCreatedEvent;
import com.example.Evenement.Events.RatingAndCommentAddedEvent;
import com.example.Evenement.Events.UserJoinedEvent;
import com.example.Evenement.QueryModels.EvenementView;
import com.example.Evenement.QueryModels.Participant;
import com.example.Evenement.QueryModels.RatingAndComment;
import com.example.Evenement.Repositories.EvenementRepository;
import com.example.Evenement.Repositories.ParticipantRepository;
import com.example.Evenement.Repositories.RatingAndCommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class EventEventHandler {
    private final EvenementRepository evenementRepository;
    private final ParticipantRepository participantRepository;
    private final RatingAndCommentRepository ratingAndCommentRepository;

    public EventEventHandler(EvenementRepository evenementRepository, ParticipantRepository participantRepository, RatingAndCommentRepository ratingAndComment) {
        this.evenementRepository = evenementRepository;
        this.participantRepository = participantRepository;
        this.ratingAndCommentRepository = ratingAndComment;
    }
    @EventHandler
    public void on(EventCreatedEvent event) {
        EvenementView evenement = new EvenementView(
                event.getEventId(),
                event.getName(),
                event.getDescription(),
                event.getStartDate(),
                event.getEndDate()
        );
        evenementRepository.save(evenement);
    }
    @EventHandler
    public void on(UserJoinedEvent event) {
        // Retrieve the evenement and participant from their respective repositories
        EvenementView evenement = evenementRepository.findById(event.getEventId()).orElseThrow(() -> new EntityNotFoundException("Event not found"));
        Participant participant = participantRepository.findById(event.getUserId()).orElseThrow(() -> new EntityNotFoundException("Participant not found"));
        evenement.getParticipants().add(participant);

        evenementRepository.save(evenement);
    }
    @EventHandler
    public void  on(RatingAndCommentAddedEvent event){
        RatingAndComment ratingAndComment = new RatingAndComment(event.getUserId(),event.getRating(),event.getComment());
        this.ratingAndCommentRepository.save(ratingAndComment);
    }

}
