package com.mgtu.beauty.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException exception) {
        return ResponseEntity.status(403).body(ExceptionResponse.builder()
                .message(exception.getMessage())
                .cause(String.valueOf(exception.getCause()))
                .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(400).body(ExceptionResponse.builder()
                .message(exception.getMessage())
                .cause(String.valueOf(exception.getCause()))
                .build());
    }

    // Обработка общего RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity.status(500).body(ExceptionResponse.builder()
                        .message(exception.getMessage())
                        .cause(String.valueOf(exception.getCause()))
                .build());
    }
}
