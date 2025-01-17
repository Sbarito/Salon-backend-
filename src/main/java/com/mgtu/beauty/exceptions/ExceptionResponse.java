package com.mgtu.beauty.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {
    private String message;
    private String cause;
}
