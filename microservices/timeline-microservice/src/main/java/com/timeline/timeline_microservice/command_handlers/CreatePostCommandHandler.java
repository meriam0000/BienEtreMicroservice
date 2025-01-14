package com.timeline.timeline_microservice.command_handlers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.timeline.timeline_microservice.commands.CreatePostCommand;
import com.timeline.timeline_microservice.events.PostCreatedEvent;
import com.timeline.timeline_microservice.models.Post;
import com.timeline.timeline_microservice.repositories.PostRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreatePostCommandHandler {
    private final PostRepository postRepository;
    private final ApplicationEventPublisher eventPublisher;
    
    @Transactional
    public String handle(CreatePostCommand command) {
        Post post = new Post();
        post.setId(UUID.randomUUID().toString());
        post.setAuthorId(command.getAuthorId());
        post.setContent(command.getContent());
        post.setCreatedAt(LocalDateTime.now());
        
        postRepository.save(post);
        
        eventPublisher.publishEvent(new PostCreatedEvent(
            post.getId(),
            post.getAuthorId(),
            post.getContent(),
            post.getCreatedAt()
        ));
        
        return post.getId();
    }
}
