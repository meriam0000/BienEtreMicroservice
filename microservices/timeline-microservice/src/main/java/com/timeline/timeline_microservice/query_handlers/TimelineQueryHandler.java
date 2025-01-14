package com.timeline.timeline_microservice.query_handlers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.timeline.timeline_microservice.DTOs.CommentDTO;
import com.timeline.timeline_microservice.DTOs.PostDTO;
import com.timeline.timeline_microservice.models.Comment;
import com.timeline.timeline_microservice.models.Post;
import com.timeline.timeline_microservice.models.ReactionType;
import com.timeline.timeline_microservice.queries.GetTimelineQuery;
import com.timeline.timeline_microservice.repositories.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimelineQueryHandler {
    private final PostRepository postRepository;
    private final UserService userService;
    
    public Page<PostDTO> handle(GetTimelineQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize(), 
            Sort.by("createdAt").descending());
            
        return postRepository.findTimelinePosts(
            query.getUserId(),
            query.getDepartmentId(),
            pageable
        ).map(post -> toDTO(post, query.getUserId()));
    }
    
    private PostDTO toDTO(Post post, String currentUserId) {
        // Fetch additional data
        String authorName = userService.getUserName(post.getAuthorId());
        
        // Calculate reactions
        long likeCount = post.getReactions().stream()
            .filter(r -> r.getType() == ReactionType.LIKE)
            .count();
            
        long dislikeCount = post.getReactions().stream()
            .filter(r -> r.getType() == ReactionType.DISLIKE)
            .count();
            
        // Check if current user has liked the post
        boolean hasUserLiked = post.getReactions().stream()
            .anyMatch(r -> r.getUserId().equals(currentUserId) && r.getType() == ReactionType.LIKE);
            
        // Get recent comments
        List<CommentDTO> recentComments = post.getComments().stream()
            .sorted(Comparator.comparing(Comment::getCreatedAt).reversed())
            .limit(3)
            .map(this::toCommentDTO)
            .collect(Collectors.toList());
            
        // Create PostDTO with all required parameters
        return new PostDTO(
            post.getId(),              // id
            post.getAuthorId(),        // authorId
            authorName,                // authorName
            post.getContent(),         // content
            post.getComments().size(), // commentCount
            likeCount,                 // likeCount
            dislikeCount,              // dislikeCount
            post.getCreatedAt(),       // createdAt
            recentComments,            // recentComments
            hasUserLiked               // hasUserLiked
        );
    }
    
    private CommentDTO toCommentDTO(Comment comment) {
        String authorName = userService.getUserName(comment.getAuthorId());
        return new CommentDTO(
            comment.getId(),
            comment.getAuthorId(),
            authorName,
            comment.getContent(),
            comment.getCreatedAt()
        );
    }
}
