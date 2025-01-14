package com.timeline.timeline_microservice.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    private String id;
    
    @ManyToOne
    private Post post;
    private String authorId;
    private String content;
    private LocalDateTime createdAt;
}
