package org.example.forummicroservice.aggregates;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import org.axonframework.modelling.command.AggregateLifecycle;
import org.example.forummicroservice.commands.CreateArticleCommand;
import org.example.forummicroservice.commands.UpdateArticleCommand;
import org.example.forummicroservice.commands.DeleteArticleCommand;
import org.example.forummicroservice.events.ArticleCreatedEvent;
import org.example.forummicroservice.events.ArticleUpdatedEvent;
import org.example.forummicroservice.events.ArticleDeletedEvent;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Aggregate
public class ArticleAggregate {

    @AggregateIdentifier
    private Long id; // The ID is now of type Long, as it’s the type of the 'id' in the entity.
    private String title;
    private String content;
    private String authorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> tags;

    // Default constructor is required by Axon Framework
    public ArticleAggregate() {}

    // Command handler for CreateArticleCommand
    @CommandHandler
    public ArticleAggregate(CreateArticleCommand command) {
        log.info("Handling CreateArticleCommand: {}", command);
        // Apply the ArticleCreatedEvent, which will update the state of the aggregate
        AggregateLifecycle.apply(new ArticleCreatedEvent(
                command.getId(),
                command.getTitle(),
                command.getContent(),
                command.getTags(),
                command.getAuthorId(),
                LocalDateTime.now(), // Setting createdAt to the current time
                null // No need for updatedAt at creation
        ));
    }

    // Command handler for UpdateArticleCommand
    @CommandHandler
    public void handle(UpdateArticleCommand command) {
        log.info("Handling UpdateArticleCommand: {}", command);
        // Apply the ArticleUpdatedEvent
        AggregateLifecycle.apply(new ArticleUpdatedEvent(
                command.getId(),
                command.getTitle(),
                command.getContent(),
                command.getTags(),
                LocalDateTime.now() // Updated time
        ));
    }

    // Command handler for DeleteArticleCommand
    @CommandHandler
    public void handle(DeleteArticleCommand command) {
        log.info("Handling DeleteArticleCommand: {}", command);
        // Apply the ArticleDeletedEvent
        AggregateLifecycle.apply(new ArticleDeletedEvent(command.getId()));
    }

    // Event handler for ArticleCreatedEvent
    @EventSourcingHandler
    public void on(ArticleCreatedEvent event) {
        // Set the internal state of the aggregate based on the event
        this.id = event.getArticleId();
        this.title = event.getTitle();
        this.content = event.getContent();
        this.tags = event.getTags();
        this.authorId = event.getAuthorId();
        this.createdAt = event.getCreatedAt();
        this.updatedAt = null;
    }

    // Event handler for ArticleUpdatedEvent
    @EventSourcingHandler
    public void on(ArticleUpdatedEvent event) {
        // Update the internal state with the new values
        this.title = event.getTitle();
        this.content = event.getContent();
        this.tags = event.getTags();
        this.updatedAt = event.getUpdatedAt();
    }

    // Event handler for ArticleDeletedEvent
    @EventSourcingHandler
    public void on(ArticleDeletedEvent event) {
        // Set the article fields to null to indicate deletion
        this.title = null;
        this.content = null;
        this.authorId = null;
        this.tags = null;
        this.createdAt = null;
        this.updatedAt = null;
    }

    // Getters and Setters for the fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
