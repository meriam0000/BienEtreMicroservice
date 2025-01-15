package org.example.forummicroservice.events;


import java.time.LocalDateTime;
import java.util.List;

public class ArticleUpdatedEvent {

    private Long articleId;  // ID of the article being updated
    private String title;     // Updated title of the article
    private String content;   // Updated content of the article
    private List<String> tags; // Updated tags of the article (if any)
    private LocalDateTime updatedAt; // Timestamp of the update

    // Constructor
    public ArticleUpdatedEvent(Long articleId, String title, String content, List<String> tags, LocalDateTime updatedAt) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

