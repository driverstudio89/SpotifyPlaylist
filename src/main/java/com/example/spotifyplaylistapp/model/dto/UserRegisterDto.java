package com.example.spotifyplaylistapp.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterDto {

    @NotNull
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Email must have @ !")
    private String email;

    @NotNull
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

    private String confirmPassword;

    public @NotNull @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!") String username) {
        this.username = username;
    }

    public @NotNull @Email(message = "Email cannot be empty!") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Email(message = "Email cannot be empty!") String email) {
        this.email = email;
    }

    public @NotNull @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!") String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
