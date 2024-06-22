package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.config.UserSession;
import com.example.spotifyplaylistapp.model.dto.AddSongDto;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.service.SongService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class SongController {


    private final SongService songService;
    private final UserSession userSession;

    public SongController(SongRepository songRepository, SongService songService, UserSession userSession) {
        this.songService = songService;
        this.userSession = userSession;
    }

    @ModelAttribute("songData")
    public AddSongDto addSongDto() {
        return new AddSongDto();
    }

    @GetMapping("/song-add")
    public String viewAddSong() {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        return "song-add";
    }

    @PostMapping("/song-add")
    public String doAddSong(
            @Valid AddSongDto addSongDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("songData", addSongDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.songData", bindingResult);
            return "redirect:/song-add";
        }

        if (addSongDto.getReleaseDate().isAfter(LocalDate.now())) {
            redirectAttributes.addFlashAttribute("songData", addSongDto);
            redirectAttributes.addFlashAttribute("releaseDateInFuture", true);
            return "redirect:/song-add";
        }

        songService.addSong(addSongDto);

        return "redirect:/home";
    }


}
