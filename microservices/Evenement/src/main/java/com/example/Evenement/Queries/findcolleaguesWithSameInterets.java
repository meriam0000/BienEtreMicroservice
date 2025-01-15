package com.example.Evenement.Queries;

import java.util.List;

public class findcolleaguesWithSameInterets {
    private final String employeId;
    private final List<String> interests;

    public findcolleaguesWithSameInterets(String employeId, List<String> interests) {
        this.employeId = employeId;
        this.interests = interests;
    }

    public String getEmployeId() {
        return employeId;
    }

    public List<String> getInterests() {
        return interests;
    }
}
