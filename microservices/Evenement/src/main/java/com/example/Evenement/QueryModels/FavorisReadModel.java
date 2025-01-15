package com.example.Evenement.QueryModels;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class FavorisReadModel {
    @Id
    private String idFavoris;

    private String userId;
    private String eventId;



    public FavorisReadModel() {
    }


    public FavorisReadModel(String favorisId, String eventId, String userId) {
        this.idFavoris = favorisId;
        this.userId = userId;
        this.eventId = eventId;
    }
}
