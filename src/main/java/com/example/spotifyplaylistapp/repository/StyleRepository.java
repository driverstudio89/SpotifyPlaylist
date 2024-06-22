package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.Style;
import com.example.spotifyplaylistapp.model.enums.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Integer> {


    Style findByName(StyleEnum name);
}
