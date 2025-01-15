package com.example.Evenement.Queries;

public class FindRatingAndCommentsByEvent {
    private String eventId;

    public FindRatingAndCommentsByEvent(String eventId) {
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }
}
