package org.example.forummicroservice.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

public class UpdateArticleCommand {
    @TargetAggregateIdentifier
    private final Long id;
    private final String title;
    private final String content;
    private final List<String> tags;

    public UpdateArticleCommand(Long id, String title, String content, List<String> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<String> getTags() {
        return tags;
    }
}


