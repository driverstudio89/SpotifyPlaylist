package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.config.Config;
import com.example.spotifyplaylistapp.model.Song;
import jakarta.validation.constraints.NotNull;

public class SongInfoDto {

    private Config config;

    @NotNull
    private long id;

    @NotNull
    private String performer;

    @NotNull
    private String title;

    @NotNull
    private Integer duration;

    public SongInfoDto(Song song) {
        this.id = song.getId();
        this.performer = song.getPerformer();
        this.title = song.getTitle();
        this.duration = song.getDuration();

    }

    @NotNull
    public long getId() {
        return id;
    }

    public void setId(@NotNull long id) {
        this.id = id;
    }

    public @NotNull String getPerformer() {
        return performer;
    }

    public void setPerformer(@NotNull String performer) {
        this.performer = performer;
    }

    public @NotNull String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public @NotNull String getDuration() {
        return toMinutes(duration);
    }

    public void setDuration(@NotNull Integer duration) {
        this.duration = duration;
    }

    public String toMinutes(Integer seconds) {
        int min = seconds / 60;
        int sec = seconds % 60;

        String result =  String.format("%d:%02d", min, sec) ;
        System.out.println(result);
        return result;
    }


}
