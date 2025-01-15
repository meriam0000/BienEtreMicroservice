package com.example.Evenement.QueryModels;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RatingAndComment {

    @Id
    private Long id;

    private int rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EvenementView event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Participant user;

    public RatingAndComment(String userId, int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
        this.user = new Participant();
        this.user.setUserId(userId);

    }

    public RatingAndComment() {

    }
}
