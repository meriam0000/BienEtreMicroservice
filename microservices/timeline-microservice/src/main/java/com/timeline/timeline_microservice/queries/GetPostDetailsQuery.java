package com.timeline.timeline_microservice.queries;

import lombok.Value;

@Value
public class GetPostDetailsQuery {
    private String postId;
}
