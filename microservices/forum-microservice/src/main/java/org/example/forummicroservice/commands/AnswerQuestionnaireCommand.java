package org.example.forummicroservice.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AnswerQuestionnaireCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final String employeeId;
    private final String question;
    private final String answer;

    public AnswerQuestionnaireCommand(String id, String employeeId, String question, String answer) {
        this.id = id;
        this.employeeId = employeeId;
        this.question = question;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
