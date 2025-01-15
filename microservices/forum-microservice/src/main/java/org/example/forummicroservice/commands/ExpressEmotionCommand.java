package org.example.forummicroservice.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ExpressEmotionCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final String emotionType;
    private final String description;

    public ExpressEmotionCommand(String id, String emotionType, String description) {
        this.id = id;
        this.emotionType = emotionType;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getEmotionType() {
        return emotionType;
    }

    public String getDescription() {
        return description;
    }
}
