package com.timeline.timeline_microservice.events;

import java.time.LocalDateTime;

import lombok.Value;

@Value
public class CommentAddedEvent {
    private String commentId;
    private String postId;
    private String authorId;
    private String content;
    private LocalDateTime createdAt;
}
