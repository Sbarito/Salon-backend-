package com.mgtu.beauty.controller.AuthController.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {
    @NonNull
    private String phone;

    @NonNull
    private String password;

    @NonNull
    private String fio;
}
