package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.Song;
import com.example.spotifyplaylistapp.model.Style;
import com.example.spotifyplaylistapp.model.enums.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {


    List<Song> findAllByStyle(Style style);
}
