package com.mgtu.beauty.service;


import com.mgtu.beauty.controller.AuthController.request.SignInRequest;
import com.mgtu.beauty.controller.AuthController.request.SignUpRequest;
import com.mgtu.beauty.controller.AuthController.response.SignInResponse;
import com.mgtu.beauty.entity.User;
import com.mgtu.beauty.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {
    private  UserRepository userRepository;
    private  TokenService tokenService;
    private  ModelMapper mapper;

    public SignInResponse signIn(SignInRequest dto) throws AccessDeniedException{
        User user = userRepository.findByPhone(dto.getPhone());
        if(user == null) {
            throw new AccessDeniedException("Неверный логин или пароль");
        }
        if (!BCrypt.checkpw(dto.getPassword(), user.getPassword())) {
            throw new AccessDeniedException("Неверный логин или пароль");
        }
        return new SignInResponse(tokenService.generateToken(dto.getPhone()), user.getRole().getName());
    }

    public SignInResponse signUp(SignUpRequest dto) throws AccessDeniedException {
        Boolean isPhoneExist = userRepository.findByPhone(dto.getPhone()) != null;
        if(isPhoneExist) {
            throw new AccessDeniedException("ПОльзователь с таким номером телефона уже зарегестрирован");
        }
        dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        userRepository.save(mapper.map(dto, User.class));
        return new SignInResponse(tokenService.generateToken(dto.getPhone()), "USER");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByPhone(username);
    }
}
