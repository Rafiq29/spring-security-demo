package com.herb.springsecuritydemo.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private HttpStatus status;
    private String error;
    private String message;
    private String path;
}
