package com.tobeto.lms.controllers;

import com.tobeto.lms.services.abstracts.AuthService;
import com.tobeto.lms.services.dtos.responses.ListUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;

    @GetMapping("userlist")
    public List<ListUserResponse> getAll(){
        return authService.getAll();
    }
}
