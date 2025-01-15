package com.example.Evenement.commandes;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Data
public class AddRatingAndCommentCommand {

    @TargetAggregateIdentifier
    private String eventId;

    private String userId;
    private int rating;
    private String comment;
    public AddRatingAndCommentCommand(String eventId, String userId, int rating, String comment) {
        this.eventId = eventId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }
}
