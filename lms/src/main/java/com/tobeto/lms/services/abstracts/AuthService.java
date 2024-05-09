package com.tobeto.lms.services.abstracts;

import com.tobeto.lms.entities.User;
import com.tobeto.lms.services.dtos.requests.LoginRequest;
import com.tobeto.lms.services.dtos.requests.RegisterRequest;
import com.tobeto.lms.services.dtos.responses.ListUserResponse;

import java.util.List;
import java.util.Optional;

public interface AuthService {
    void register(RegisterRequest request);

    String login(LoginRequest request);

    List<ListUserResponse> getAll();

    Optional<User> findById(int id);

    User save(User user);

}
