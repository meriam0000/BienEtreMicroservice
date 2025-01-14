package com.timeline.timeline_microservice.events;

import com.timeline.timeline_microservice.models.ReactionType;

import lombok.Value;

@Value
public class ReactionAddedEvent {
    private String reactionId;
    private String postId;
    private String userId;
    private ReactionType type;
}