package org.example.forummicroservice.controllers;

import jakarta.validation.Valid;
import org.example.forummicroservice.DTOs.ArticleDTO;
import org.example.forummicroservice.commands.CreateArticleCommand;
import org.example.forummicroservice.commands.UpdateArticleCommand;
import org.example.forummicroservice.commands.DeleteArticleCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    private final CommandGateway commandGateway;

    // Using AtomicLong for generating unique Long IDs
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    @Autowired
    public ArticleController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    // Create article endpoint
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "articleService", fallbackMethod = "fallbackCreateArticle")
    @Retry(name = "articleService")
    public void createArticle(@Valid @RequestBody ArticleDTO articleDTO) {
        Long articleId = ID_GENERATOR.getAndIncrement(); // Generate unique Long ID for the article
        CreateArticleCommand command = new CreateArticleCommand(
                articleId,
                articleDTO.getTitle(),
                articleDTO.getContent(),
                articleDTO.getTags(),
                articleDTO.getAuthorId()
        );
        commandGateway.sendAndWait(command); // Sending the command to the command bus
    }

    // Update article endpoint
    @PutMapping("/{id}")
    @CircuitBreaker(name = "articleService", fallbackMethod = "fallbackUpdateArticle")
    @Retry(name = "articleService")
    public void updateArticle(@PathVariable Long id, @Valid @RequestBody ArticleDTO articleDTO) {
        UpdateArticleCommand command = new UpdateArticleCommand(
                id,
                articleDTO.getTitle(),
                articleDTO.getContent(),
                articleDTO.getTags()
        );
        commandGateway.sendAndWait(command);
    }

    // Delete article endpoint
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CircuitBreaker(name = "articleService", fallbackMethod = "fallbackDeleteArticle")
    @Retry(name = "articleService")
    public void deleteArticle(@PathVariable Long id) {
        DeleteArticleCommand command = new DeleteArticleCommand(id);
        commandGateway.sendAndWait(command);
    }

    // Fallback methods
    public void fallbackCreateArticle(ArticleDTO articleDTO, Throwable throwable) {
        log.error("Fallback for createArticle: {}", throwable.getMessage());
        // Add additional fallback handling logic here if needed
    }

    public void fallbackUpdateArticle(Long id, ArticleDTO articleDTO, Throwable throwable) {
        log.error("Fallback for updateArticle: {}", throwable.getMessage());
        // Add additional fallback handling logic here if needed
    }

    public void fallbackDeleteArticle(Long id, Throwable throwable) {
        log.error("Fallback for deleteArticle: {}", throwable.getMessage());
        // Add additional fallback handling logic here if needed
    }
}
