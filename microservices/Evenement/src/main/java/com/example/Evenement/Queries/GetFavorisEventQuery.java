package com.example.Evenement.Queries;

public class GetFavorisEventQuery {
    private String userId;

    public GetFavorisEventQuery(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
