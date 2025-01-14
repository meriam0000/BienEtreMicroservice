package com.timeline.timeline_microservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.timeline.timeline_microservice.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    Page<Post> findTimelinePosts(String userId, String departmentId, Pageable pageable);
}
