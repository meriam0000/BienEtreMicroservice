package com.timeline.timeline_microservice.command_handlers;

import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.timeline.timeline_microservice.commands.AddReactionCommand;
import com.timeline.timeline_microservice.models.Post;
import com.timeline.timeline_microservice.models.Reaction;
import com.timeline.timeline_microservice.repositories.PostRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.timeline.timeline_microservice.events.ReactionAddedEvent;

@Service
@RequiredArgsConstructor
public class AddReactionCommandHandler {
    private final PostRepository postRepository;
    private final ApplicationEventPublisher eventPublisher;
    
    @Transactional
    public void handle(AddReactionCommand command) {
        Post post = postRepository.findById(command.getPostId())
            .orElseThrow(() -> new EntityNotFoundException("Post not found"));
            
        // Remove existing reaction from the same user if exists
        post.getReactions().removeIf(r -> r.getUserId().equals(command.getUserId()));
        
        // Add new reaction
        Reaction reaction = new Reaction();
        reaction.setId(UUID.randomUUID().toString());
        reaction.setPost(post);
        reaction.setUserId(command.getUserId());
        reaction.setType(command.getType());
        
        post.getReactions().add(reaction);
        postRepository.save(post);
        
        eventPublisher.publishEvent(new ReactionAddedEvent(
            reaction.getId(),
            post.getId(),
            reaction.getUserId(),
            reaction.getType()
        ));
    }
}
