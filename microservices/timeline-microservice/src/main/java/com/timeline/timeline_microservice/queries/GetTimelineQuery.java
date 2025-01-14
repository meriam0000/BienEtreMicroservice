package com.timeline.timeline_microservice.queries;

import lombok.Value;

@Value
public class GetTimelineQuery {
    private String userId;
    private String departmentId;
    private int page;
    private int size;
}
