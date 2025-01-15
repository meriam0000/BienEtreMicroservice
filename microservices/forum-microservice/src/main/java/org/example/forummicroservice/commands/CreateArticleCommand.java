package org.example.forummicroservice.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

public class CreateArticleCommand {
    @TargetAggregateIdentifier
    private final Long id;
    private final String title;
    private final String content;
    private final List<String> tags;
    private final String authorId;

    public CreateArticleCommand(Long id, String title, String content, List<String> tags, String authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.authorId = authorId;
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

    public String getAuthorId() {
        return authorId;
    }
}
