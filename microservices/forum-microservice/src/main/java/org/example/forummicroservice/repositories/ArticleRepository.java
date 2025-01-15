package org.example.forummicroservice.repositories;


import org.example.forummicroservice.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    // Find article by ID
    Optional<Article> findById(Long id);

    // Find articles by authorId
    List<Article> findByAuthorId(String authorId);

    // Find articles by tag
    List<Article> findByTagsContaining(String tag);

    // Custom method to fetch articles by their creation date range
    List<Article> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Delete article by ID
    void deleteById(Long id);

    // Find all articles (optional, since JpaRepository already provides this method)
    List<Article> findAll();

    // Insert new Article or update existing one
    @Override
    <S extends Article> S save(S article); // This method handles both inserting and updating

    // Update article by id (optional, if you want custom update logic)
    default Article updateArticle(Long id, String title, String content, List<String> tags, String authorId) {
        // Find the article by ID
        Optional<Article> existingArticle = findById(id);

        if (existingArticle.isPresent()) {
            // If article exists, update its fields
            Article article = existingArticle.get();
            article.setTitle(title);
            article.setContent(content);
            article.setTags(tags);
            article.setAuthorId(authorId);  // This might not change often, but if you want to allow it
            // Save updated article
            return save(article);
        }

        // Return null if article doesn't exist
        return null;
    }
}

