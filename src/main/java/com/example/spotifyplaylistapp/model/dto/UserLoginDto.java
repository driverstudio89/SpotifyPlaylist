package com.example.spotifyplaylistapp.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDto {

    @NotNull
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @NotNull
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

    public @NotNull @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!") String username) {
        this.username = username;
    }

    public @NotNull @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!") String password) {
        this.password = password;
    }
}
