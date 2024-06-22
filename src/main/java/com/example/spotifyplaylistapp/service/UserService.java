package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.config.Config;
import com.example.spotifyplaylistapp.config.UserSession;
import com.example.spotifyplaylistapp.model.Song;
import com.example.spotifyplaylistapp.model.User;
import com.example.spotifyplaylistapp.model.dto.SongInfoDto;
import com.example.spotifyplaylistapp.model.dto.UserLoginDto;
import com.example.spotifyplaylistapp.model.dto.UserRegisterDto;
import com.example.spotifyplaylistapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {


    private final UserSession userSession;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Config config;

    public UserService(UserSession userSession, UserRepository userRepository, PasswordEncoder passwordEncoder, Config config) {
        this.userSession = userSession;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.config = config;
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


    public boolean login(UserLoginDto userLoginDto) {
        if (userSession.isLoggedIn()) {
            return false;
        }

        Optional<User> byUsername = userRepository.findByUsername(userLoginDto.getUsername());
        if (byUsername.isEmpty()) {
            return false;
        }

        if (!passwordEncoder.matches(userLoginDto.getPassword(), byUsername.get().getPassword())) {
            return false;
        }

        User user = byUsername.get();
        userSession.login(user.getId(), user.getUsername());

        return true;
    }

    public List<SongInfoDto> currentUserPlaylist() {
        return userRepository.findById(userSession.getId()).get().getPlaylist().stream().map(SongInfoDto::new).toList();
    }

    @Transactional
    public void removeAllSongs() {
        User user = userRepository.findById(userSession.getId()).get();
        user.getPlaylist().clear();
        userRepository.save(user);
    }

    public String totalPlaylistDuration() {
        Integer totalDuration = 0;
        List<Song> list = userRepository.findById(userSession.getId()).get().getPlaylist().stream().toList();
        for (Song song : list) {
            totalDuration += song.getDuration();
        }
        return toMinutes(totalDuration);
    }

    public String toMinutes(Integer seconds) {
        int min = seconds / 60;
        int sec = seconds % 60;

        String result =  String.format("%d:%02d", min, sec) ;
        System.out.println(result);
        return result;
    }
}
