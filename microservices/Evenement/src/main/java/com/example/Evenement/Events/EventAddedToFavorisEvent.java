package com.example.Evenement.Events;

import lombok.Data;

@Data
public class EventAddedToFavorisEvent {
    private String userId;
    private String eventId;
    private String favorisId;

    public EventAddedToFavorisEvent(String favorisId,String userId, String eventId) {
        this.userId = userId;
        this.eventId = eventId;
        this.favorisId = favorisId;
    }



}
