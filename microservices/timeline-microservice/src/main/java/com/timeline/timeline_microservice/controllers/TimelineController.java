package com.timeline.timeline_microservice.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timeline.timeline_microservice.DTOs.PostDTO;
import com.timeline.timeline_microservice.command_handlers.AddCommentCommandHandler;
import com.timeline.timeline_microservice.command_handlers.AddReactionCommandHandler;
import com.timeline.timeline_microservice.command_handlers.CreatePostCommandHandler;
import com.timeline.timeline_microservice.commands.AddCommentCommand;
import com.timeline.timeline_microservice.commands.AddReactionCommand;
import com.timeline.timeline_microservice.commands.CreatePostCommand;
import com.timeline.timeline_microservice.queries.GetTimelineQuery;
import com.timeline.timeline_microservice.query_handlers.TimelineQueryHandler;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/timeline")
@RequiredArgsConstructor
public class TimelineController {
    private final CreatePostCommandHandler createPostCommandHandler;
    private final AddCommentCommandHandler addCommentCommandHandler;
    private final AddReactionCommandHandler addReactionCommandHandler;
    private final TimelineQueryHandler timelineQueryHandler;
    
    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@RequestBody CreatePostCommand command) {
        String postId = createPostCommandHandler.handle(command);
        return ResponseEntity.ok(postId);
    }
    
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<String> addComment(
            @PathVariable String postId,
            @RequestBody AddCommentCommand command) {
        String commentId = addCommentCommandHandler.handle(command);
        return ResponseEntity.ok(commentId);
    }
    
    @PostMapping("/posts/{postId}/reactions")
    public ResponseEntity<Void> addReaction(
            @PathVariable String postId,
            @RequestBody AddReactionCommand command) {
        addReactionCommandHandler.handle(command);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<Page<PostDTO>> getTimeline(
            @RequestParam String userId,
            @RequestParam String departmentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        GetTimelineQuery query = new GetTimelineQuery(userId, departmentId, page, size);
        Page<PostDTO> timeline = timelineQueryHandler.handle(query);
        return ResponseEntity.ok(timeline);
    }
}
