package com.tobeto.lms.controllers;

import com.tobeto.lms.services.abstracts.AuthService;
import com.tobeto.lms.services.dtos.requests.LoginRequest;
import com.tobeto.lms.services.dtos.requests.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("register")
    public String register(@RequestBody @Valid RegisterRequest request){
        authService.register(request);
        return "Kayıt Olma İşlemi Başarılı" ;
    }

    @PostMapping("login")
    public String login(@RequestBody @Valid LoginRequest request){
        return  authService.login(request);
    }

    /*@GetMapping
    public List<ListUserResponse> getAll(){
     return userService.getAll();
    }*/
}
