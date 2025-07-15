package kh.edu.banking.api.dto;

import lombok.Builder;

@Builder
public record CustomerSegmentResponse (
        Integer id,
        String name
){}
