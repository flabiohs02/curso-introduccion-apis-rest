package com.cursoapis.curso_introduccion_apis_rest.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Standard error response structure for API errors.
 */
@Data
@Builder
public class ErrorResponse {

    private String error;
    private String message;
    private int status;
    private String path;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
