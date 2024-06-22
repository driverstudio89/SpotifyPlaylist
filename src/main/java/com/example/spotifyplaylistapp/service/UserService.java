package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.config.UserSession;
import com.example.spotifyplaylistapp.model.User;
import com.example.spotifyplaylistapp.model.dto.UserRegisterDto;
import com.example.spotifyplaylistapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final UserSession userSession;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserSession userSession, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userSession = userSession;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean register(UserRegisterDto userRegisterDto) {
        if (userSession.isLoggedIn()) {
            return false;
        }

        Optional<User> byUsernameOrEmail = userRepository.findByUsernameOrEmail(userRegisterDto.getUsername(), userRegisterDto.getEmail());
        if (byUsernameOrEmail.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(userRegisterDto.getUsername());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        userRepository.save(user);
        return true;
    }



}
