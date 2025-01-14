package com.timeline.timeline_microservice.DTOs;

import java.time.LocalDateTime;

import lombok.Value;

@Value
public class CommentDTO {
    private String id;
    private String authorId;
    private String authorName;
    private String content;
    private LocalDateTime createdAt;
}
