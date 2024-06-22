package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.config.UserSession;
import com.example.spotifyplaylistapp.model.Song;
import com.example.spotifyplaylistapp.model.Style;
import com.example.spotifyplaylistapp.model.dto.AddSongDto;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.stereotype.Service;

@Service
public class SongService {


    private final UserSession userSession;
    private final SongRepository songRepository;
    private final StyleRepository styleRepository;

    public SongService(UserSession userSession, SongRepository songRepository, StyleRepository styleRepository) {
        this.userSession = userSession;
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;
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
}
