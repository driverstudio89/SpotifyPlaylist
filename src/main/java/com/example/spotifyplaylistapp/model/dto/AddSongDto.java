package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.enums.StyleEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddSongDto {

    @NotNull
    @Size(min = 3, max = 20, message = "Performer name length must be between 3 and 20 characters!")
    private String performer;

    @NotNull
    @Size(min = 2, max = 20, message = "Title length must be between 2 and 20 characters!")
    private String title;

    @NotNull
    private LocalDate releaseDate;

    @NotNull(message = "You must enter duration!")
    @Positive(message = "Duration must be positive!")
    private Integer duration;

    @NotNull(message = "You must select a style")
    private StyleEnum style;

    public @NotNull @Size(min = 3, max = 20, message = "Performer name length must be between 3 and 20 characters!") String getPerformer() {
        return performer;
    }

    public void setPerformer(@NotNull @Size(min = 3, max = 20, message = "Performer name length must be between 3 and 20 characters!") String performer) {
        this.performer = performer;
    }

    public @NotNull @Size(min = 2, max = 20, message = "Title length must be between 2 and 20 characters!") String getTitle() {
        return title;
    }

    public void setTitle(@NotNull @Size(min = 2, max = 20, message = "Title length must be between 2 and 20 characters!") String title) {
        this.title = title;
    }

    public @NotNull LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@NotNull LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public @NotNull(message = "You must enter duration!") @Positive(message = "Duration must be positive!") Integer getDuration() {
        return duration;
    }

    public void setDuration(@NotNull(message = "You must enter duration!") @Positive(message = "Duration must be positive!") Integer duration) {
        this.duration = duration;
    }

    public @NotNull(message = "You must select a style") StyleEnum getStyle() {
        return style;
    }

    public void setStyle(@NotNull(message = "You must select a style") StyleEnum style) {
        this.style = style;
    }
}
