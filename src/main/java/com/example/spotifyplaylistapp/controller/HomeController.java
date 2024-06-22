package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.config.UserSession;
import com.example.spotifyplaylistapp.model.dto.SongInfoDto;
import com.example.spotifyplaylistapp.model.enums.StyleEnum;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final UserSession userSession;
    private final SongService songService;
    private final UserService userService;

    public HomeController(UserSession userSession, SongService songService, UserService userService) {
        this.userSession = userSession;
        this.songService = songService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String notLoggedIn() {
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @Transactional
    @GetMapping("/home")
    public String loggedIn(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        Map<StyleEnum, List<SongInfoDto>> allByStyle = songService.findAllByStyle();
        List<SongInfoDto> pop = allByStyle.get(StyleEnum.POP);
        List<SongInfoDto> rock = allByStyle.get(StyleEnum.ROCK);
        List<SongInfoDto> jazz = allByStyle.get(StyleEnum.JAZZ);

        List<SongInfoDto> currentUserPlaylist = userService.currentUserPlaylist();

        model.addAttribute("popData", pop);
        model.addAttribute("rockData", rock);
        model.addAttribute("jazzData", jazz);

        model.addAttribute("currentUserPlaylist", currentUserPlaylist);

        return "home";
    }

}
