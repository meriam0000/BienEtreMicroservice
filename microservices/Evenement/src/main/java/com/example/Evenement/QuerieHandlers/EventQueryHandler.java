package com.example.Evenement.QuerieHandlers;

import com.example.Evenement.Queries.FindAllEvents;
import com.example.Evenement.Queries.FindRatingAndCommentsByEvent;
import com.example.Evenement.QueryModels.EvenementView;
import com.example.Evenement.QueryModels.RatingAndComment;
import com.example.Evenement.Repositories.EvenementRepository;
import com.example.Evenement.Repositories.RatingAndCommentRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventQueryHandler {
    private final EvenementRepository evenementRepository;
    private final RatingAndCommentRepository ratingAndCommentRepository;

    public EventQueryHandler(EvenementRepository evenementRepository, RatingAndCommentRepository ratingAndCommentRepository) {
        this.evenementRepository = evenementRepository;
        this.ratingAndCommentRepository = ratingAndCommentRepository;
    }
    @QueryHandler
    public List<EvenementView> handle(FindAllEvents query) {
         return evenementRepository.findAll();
    }
    @QueryHandler
    public List<RatingAndComment> handle(FindRatingAndCommentsByEvent query) {
        EvenementView evenement=evenementRepository.findById(query.getEventId()).orElse(null);
        return ratingAndCommentRepository.findRatingAndCommentByEvent(evenement);
    }


    }

