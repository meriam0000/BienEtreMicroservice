package com.example.Evenement.Controllers;

import com.example.Evenement.Queries.FindAllEvents;
import com.example.Evenement.Queries.FindRatingAndCommentsByEvent;
import com.example.Evenement.QueryModels.EvenementView;
import com.example.Evenement.QueryModels.RatingAndComment;
import com.example.Evenement.Services.EventService;
import com.example.Evenement.commandes.AddRatingAndCommentCommand;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/evenements")
public class EvenementController {
    private final EventService evenementService;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    public EvenementController(EventService evenementService, QueryGateway queryGateway, CommandGateway commandGateway) {
        this.evenementService = evenementService;
        this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEvenement(@RequestParam(value = "name") String name,
                                                  @RequestParam(value = "description") String description,
                                                  @RequestParam(value = "startDate") LocalDateTime startDate,
                                                  @RequestParam(value = "endDate") LocalDateTime endDate) {
        evenementService.createEvent(name, description, startDate, endDate);
        return ResponseEntity.ok("Evenement created");
    }
    @Retry(name = "product-service-retry", fallbackMethod = "fallbackForGetEvenements")
    @RateLimiter(name = "product-service-rate-limiter", fallbackMethod = "fallbackForGetEvenements")
    @CircuitBreaker(name = "product-service-circuit-breaker", fallbackMethod = "fallbackForGetEvenements")
    @GetMapping("/")
    public ResponseEntity<List<EvenementView>> getEvenements() {
        /*List<EvenementView> Evenements = queryGateway
                .query(new FindAllEvents(), ResponseTypes.multipleInstancesOf(EvenementView.class))
                .join();*/
        throw new RuntimeException("Simulated failure");
       /* return ResponseEntity.ok(Evenements);*/
    }



    @PostMapping("/join")
    public String joinEvent(@RequestParam(value = "eventId") String eventId, @RequestParam(value = "userId") String userId) {
        evenementService.joinEvent(eventId, userId);
        return "User has joined the event";
    }

    @PostMapping("/rating-comment")
    public ResponseEntity<Void> addRatingAndComment(
            @RequestParam(value = "eventId") String eventId, @RequestParam(value = "userId") String userId, @RequestParam(value = "rating") int rating, @RequestParam(value = "comment") String comment) {


            AddRatingAndCommentCommand command = new AddRatingAndCommentCommand(eventId, userId, rating, comment);
            // Set the eventId for the command
            commandGateway.sendAndWait(command);  // Dispatch the command to the command handler

            return ResponseEntity.ok().build();
        }

    @GetMapping("/events/{eventId}/ratings-comments")
    public CompletableFuture<List<RatingAndComment>> getRatingsAndComments(@RequestParam String eventId) {
        return queryGateway.query(
                new FindRatingAndCommentsByEvent(eventId),
                ResponseTypes.multipleInstancesOf(RatingAndComment.class)
        );
    }
    // Fallback method
    public ResponseEntity<List<EvenementView>> fallbackForGetEvenements(Throwable throwable) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Collections.emptyList());
    }





}
