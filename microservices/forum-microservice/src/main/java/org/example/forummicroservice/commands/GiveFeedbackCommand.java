package org.example.forummicroservice.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class GiveFeedbackCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final String employeeId;
    private final String feedbackText;

    public GiveFeedbackCommand(String id, String employeeId, String feedbackText) {
        this.id = id;
        this.employeeId = employeeId;
        this.feedbackText = feedbackText;
    }

    public String getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getFeedbackText() {
        return feedbackText;
    }
}
