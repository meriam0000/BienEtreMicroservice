package com.timeline.timeline_microservice.commands;

import com.timeline.timeline_microservice.models.ReactionType;

import lombok.Value;

@Value
public class AddReactionCommand {
    private String postId;
    private String userId;
    private ReactionType type; // LIKE, DISLIKE
}
