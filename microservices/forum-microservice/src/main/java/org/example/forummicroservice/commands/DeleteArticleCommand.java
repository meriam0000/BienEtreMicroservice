package org.example.forummicroservice.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public class DeleteArticleCommand {
    @TargetAggregateIdentifier
    private final Long id;

    public DeleteArticleCommand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
