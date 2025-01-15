package org.example.forummicroservice.events;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleCreatedEvent {
    private Long articleId;
    private String title;
    private String content;
    private List<String> tags;
    private String authorId;
    private LocalDateTime createdAt;

    public ArticleCreatedEvent(Long articleId, String title, String content, List<String> tags, String authorId, LocalDateTime createdAt, Object o) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.authorId = authorId;
        this.createdAt = createdAt;
    }

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
}
