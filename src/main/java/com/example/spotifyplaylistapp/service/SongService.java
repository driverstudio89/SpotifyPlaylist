package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.config.UserSession;
import com.example.spotifyplaylistapp.model.Song;
import com.example.spotifyplaylistapp.model.Style;
import com.example.spotifyplaylistapp.model.User;
import com.example.spotifyplaylistapp.model.dto.AddSongDto;
import com.example.spotifyplaylistapp.model.dto.SongInfoDto;
import com.example.spotifyplaylistapp.model.enums.StyleEnum;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SongService {


    private final UserSession userSession;
    private final SongRepository songRepository;
    private final StyleRepository styleRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public SongService(UserSession userSession, SongRepository songRepository, StyleRepository styleRepository, UserService userService, UserRepository userRepository) {
        this.userSession = userSession;
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public boolean addSong(AddSongDto addSongDto) {
        if (!userSession.isLoggedIn()) {
            return false;
        }

        Song song = new Song();
        Style style = styleRepository.findByName(addSongDto.getStyle());
        song.setPerformer(addSongDto.getPerformer());
        song.setTitle(addSongDto.getTitle());
        song.setReleaseDate(addSongDto.getReleaseDate());
        song.setDuration(addSongDto.getDuration());
        song.setStyle(style);

        songRepository.save(song);

        return true;
    }


    public Map<StyleEnum, List<SongInfoDto>> findAllByStyle() {
        Map<StyleEnum, List<SongInfoDto>> map = new HashMap<>();

        List<SongInfoDto> pop = songRepository.findAllByStyle(styleRepository.findByName(StyleEnum.POP))
                .stream().map(SongInfoDto::new).toList();

        List<SongInfoDto> rock = songRepository.findAllByStyle(styleRepository.findByName(StyleEnum.ROCK))
                .stream().map(SongInfoDto::new).toList();

        List<SongInfoDto> jazz = songRepository.findAllByStyle(styleRepository.findByName(StyleEnum.JAZZ))
                .stream().map(SongInfoDto::new).toList();

        map.put(StyleEnum.POP, pop);
        map.put(StyleEnum.ROCK, rock);
        map.put(StyleEnum.JAZZ, jazz);
        return map;
    }

    @Transactional
    public void addSongToPlaylist(long id) {
        Song song = songRepository.findById(id).orElse(null);

        User user = userRepository.findById(userSession.getId()).orElse(null);

        user.getPlaylist().add(song);
        userRepository.save(user);
    }

}
