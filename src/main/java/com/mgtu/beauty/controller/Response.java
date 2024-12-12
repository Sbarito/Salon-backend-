package com.mgtu.beauty.controller;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response<T> {
    private String message;
    private T data;
}
