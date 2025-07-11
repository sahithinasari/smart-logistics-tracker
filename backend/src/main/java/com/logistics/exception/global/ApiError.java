package com.logistics.exception.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    private final String message;
    private final int status;
    private final LocalDateTime timestamp;
    private final String path;
    private final String details;
}

