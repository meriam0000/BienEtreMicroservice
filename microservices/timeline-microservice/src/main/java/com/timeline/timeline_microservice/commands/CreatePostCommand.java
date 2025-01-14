package com.timeline.timeline_microservice.commands;

import lombok.Value;

@Value
public class CreatePostCommand {
    private String authorId;
    private String content;
    private String departmentId;
    private boolean isCompanyWide;
}