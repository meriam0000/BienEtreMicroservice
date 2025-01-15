package com.example.Evenement.QueryModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class EvenementView {
     @Id
        private String eventId;
        private String name;
        private String description;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
    @ManyToMany (fetch=FetchType.EAGER)
    @JoinTable(
            name = "event_participants",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonManagedReference  // Use this to handle the serialization on the owning side of the relationship
    private Set<Participant> participants ;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<RatingAndComment> ratingAndComments = new HashSet<>();
    @ManyToOne
    private FavorisReadModel favorisReadModel;


        public EvenementView() {}

        public EvenementView(String eventId, String name, String description, LocalDateTime startDate, LocalDateTime endDate) {
            this.eventId = eventId;
            this.name = name;
            this.description = description;
            this.startDate = startDate;
            this.endDate = endDate;
        }
}

