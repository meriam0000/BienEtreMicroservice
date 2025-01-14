package com.timeline.timeline_microservice.DTOs;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Value;

@Value
public class PostDTO {
    private String id;
    private String authorId;
    private String authorName;  // Added to display user's name
    private String content;
    private int commentCount;
    private long likeCount;
    private long dislikeCount;
    private LocalDateTime createdAt;
    private List<CommentDTO> recentComments;  // Added to show latest few comments
    private boolean hasUserLiked;  // Added to show if current user has liked
    
    // Constructor for all fields
    public PostDTO(
        String id,
        String authorId,
        String authorName,
        String content,
        int commentCount,
        long likeCount,
        long dislikeCount,
        LocalDateTime createdAt,
        List<CommentDTO> recentComments,
        boolean hasUserLiked
    ) {
        this.id = id;
        this.authorId = authorId;
        this.authorName = authorName;
        this.content = content;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.createdAt = createdAt;
        this.recentComments = recentComments;
        this.hasUserLiked = hasUserLiked;
    }
}
