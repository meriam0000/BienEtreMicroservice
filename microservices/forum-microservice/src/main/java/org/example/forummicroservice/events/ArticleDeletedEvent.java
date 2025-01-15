package org.example.forummicroservice.events;

public class ArticleDeletedEvent {

    private Long articleId;  // ID of the article being deleted

    // Constructor
    public ArticleDeletedEvent(Long articleId) {
        this.articleId = articleId;
    }

    // Getter and Setter
    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
