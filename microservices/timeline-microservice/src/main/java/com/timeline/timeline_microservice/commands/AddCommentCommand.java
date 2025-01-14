package com.timeline.timeline_microservice.commands;

import lombok.Value;

@Value
public class AddCommentCommand {
    private String postId;
    private String authorId;
    private String content;
}