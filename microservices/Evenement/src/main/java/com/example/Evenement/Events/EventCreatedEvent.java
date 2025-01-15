package com.example.Evenement.Events;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventCreatedEvent {
    private final String eventId;
    private final String name;
    private final String description;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public EventCreatedEvent(String eventId, String name, String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.eventId = eventId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }




}
