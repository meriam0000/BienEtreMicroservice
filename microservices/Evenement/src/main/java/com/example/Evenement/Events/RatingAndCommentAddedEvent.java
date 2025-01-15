package com.example.Evenement.Events;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RatingAndCommentAddedEvent {
    private String eventId;
    private String userId;
    private int rating;
    private String comment;

    public RatingAndCommentAddedEvent(String eventId, String userId, int rating, String comment) {
        this.eventId = eventId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }
}
