package com.example.Evenement.Events;

import lombok.Data;

@Data
public class UserJoinedEvent {
    private String eventId;
    private String userId;
    public UserJoinedEvent(String eventId, String userId) {
        this.eventId = eventId;
        this.userId = userId;
    }

}
