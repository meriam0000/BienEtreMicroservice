package com.example.Evenement.QueryModels;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Participant {
    @Id
    private String userId;
    private String name;
    private String email;
    @ManyToMany(mappedBy = "participants")
    @JsonBackReference  // This breaks the circular reference during serialization
    private Set<EvenementView> eventsParticipated ;
    @OneToMany(mappedBy = "user")
    private Set<RatingAndComment> ratingAndComments = new HashSet<>();



    public Participant() {

    }

}
