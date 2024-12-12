package com.mgtu.beauty.controller.AuthController;

import com.mgtu.beauty.controller.Response;
import com.mgtu.beauty.controller.AuthController.request.SignInRequest;
import com.mgtu.beauty.controller.AuthController.request.SignUpRequest;
import com.mgtu.beauty.controller.AuthController.response.SignInResponse;
import com.mgtu.beauty.service.AuthService;
import com.mgtu.beauty.service.TokenService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@AllArgsConstructor
@RequestMapping("/beauty/auth")
public class AuthController {
    private  AuthService authService;

    @PostMapping("sign-in")
    public ResponseEntity<Response<SignInResponse>> signIn(@RequestBody SignInRequest signInDto) throws AccessDeniedException {
        return ResponseEntity.ok(Response.<SignInResponse>builder()
                        .data(authService.signIn(signInDto))
                        .message("Добро пожаловать на сайт нашего салона красоты!")
                .build());
    }

    @PostMapping("sign-up")
    public ResponseEntity<Response<SignInResponse>> signUp(@RequestBody SignUpRequest signUpDto) throws AccessDeniedException {
        return ResponseEntity.ok(Response.
                <SignInResponse>builder()
                        .data(authService.signUp(signUpDto))
                        .message("Вы успешно зарегестрировались")
                .build());
    }
}
