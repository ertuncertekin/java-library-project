package com.tobeto.lms.services.concretes;

import com.tobeto.lms.core.services.JwtService;
import com.tobeto.lms.core.utils.exceptions.types.BusinessException;
import com.tobeto.lms.entities.User;
import com.tobeto.lms.repositories.UserRepository;
import com.tobeto.lms.services.abstracts.AuthService;
import com.tobeto.lms.services.dtos.requests.LoginRequest;
import com.tobeto.lms.services.dtos.requests.RegisterRequest;
import com.tobeto.lms.services.dtos.responses.ListUserResponse;
import com.tobeto.lms.services.mappers.AuthMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public void register(RegisterRequest request) {
        sameEmailCheck(request.getEmail());
        User user=new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
    }

    @Override
    public String login(LoginRequest request) {
        User user=userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException("Eposta Veya Şifre Hatalı"));

        Authentication authentication=authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
                );
        if(!authentication.isAuthenticated()){
            throw new BadCredentialsException("Eposta Veya Şifre Hatalı");
        }

        Map<String, Object> extraClaims=new HashMap<>();
        extraClaims.put("UserId",user.getId());
        extraClaims.put("UserName",user.getName());
        extraClaims.put("UserRole",user.getRole().name());
        return jwtService.generateToken(user.getUsername(),extraClaims);
    }

    @Override
    public List<ListUserResponse> getAll() {
        List<User> users=userRepository.findAll();
        List<ListUserResponse> list=users
                .stream()
                .map(user-> AuthMapper.INSTANCE.listResponseFromUser(user))
                .toList();
        return list;
    }


    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    private void sameEmailCheck(String email){
        Optional<User> user=userRepository.findByEmail(email);
        if(user.isPresent()){
            throw new BusinessException("Böyle Bir Mail Sistemde Zaten Kayıtlı!");
        }
    }
}
