package com.timeline.timeline_microservice.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "reactions")
public class Reaction {
    @Id
    private String id = UUID.randomUUID().toString();
    
    @ManyToOne
    private Post post;
    
    private String userId;
    private ReactionType type;
}
