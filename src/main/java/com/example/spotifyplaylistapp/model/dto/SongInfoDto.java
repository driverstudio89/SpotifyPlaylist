package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.Song;
import jakarta.validation.constraints.NotNull;

public class SongInfoDto {

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

    public void setDuration(@NotNull Integer duration) {
        this.duration = duration;
    }


    public String getDuration() {
        int minutes = duration / 60;
        int seconds = duration % 60;

        String result =  String.format("%d:%02d", minutes, seconds) ;
            System.out.println(result);
            return result;
    }
}
