package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.Style;
import com.example.spotifyplaylistapp.model.enums.StyleEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StyleService {

    private final StyleRepository styleRepository;

    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public void initStyles() {
        if (this.styleRepository.count() > 0) {
            return;
        }

        Arrays.stream(StyleEnum.values())
                .forEach(s -> {
                    Style style = new Style();
                    style.setName(s);
                    this.styleRepository.save(style);
                });

    }

}
