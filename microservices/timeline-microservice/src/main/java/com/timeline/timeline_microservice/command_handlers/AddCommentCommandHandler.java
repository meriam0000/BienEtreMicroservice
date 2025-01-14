package com.timeline.timeline_microservice.command_handlers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.timeline.timeline_microservice.commands.AddCommentCommand;
import com.timeline.timeline_microservice.models.Comment;
import com.timeline.timeline_microservice.models.Post;
import com.timeline.timeline_microservice.repositories.PostRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.timeline.timeline_microservice.events.CommentAddedEvent;

@Service
@RequiredArgsConstructor
public class AddCommentCommandHandler {
    private final PostRepository postRepository;
    private final ApplicationEventPublisher eventPublisher;
    
    @Transactional
    public String handle(AddCommentCommand command) {
        Post post = postRepository.findById(command.getPostId())
            .orElseThrow(() -> new EntityNotFoundException("Post not found"));
            
        Comment comment = new Comment();
        comment.setId(UUID.randomUUID().toString());
        comment.setPost(post);
        comment.setAuthorId(command.getAuthorId());
        comment.setContent(command.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        
        post.getComments().add(comment);
        postRepository.save(post);
        
        eventPublisher.publishEvent(new CommentAddedEvent(
            comment.getId(),
            post.getId(),
            comment.getAuthorId(),
            comment.getContent(),
            comment.getCreatedAt()
        ));
        
        return comment.getId();
    }
}
