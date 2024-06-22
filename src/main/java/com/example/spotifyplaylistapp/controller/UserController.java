package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.UserRegisterDto;
import com.example.spotifyplaylistapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerData")
    public UserRegisterDto userRegisterDto() {
        return new UserRegisterDto();
    }

    @GetMapping("/users/register")
    public String viewRegister() {
        return "register";
    }

    @PostMapping("/users/register")
    public String doRegister(
            @Valid UserRegisterDto userRegisterDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData", userRegisterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);
            return "redirect:/users/register";
        }

        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("registerData", userRegisterDto);
            redirectAttributes.addFlashAttribute("passwordMismatch", true);
            return "redirect:/users/register";
        }

        if (!userService.register(userRegisterDto)) {
            redirectAttributes.addFlashAttribute("registerData", userRegisterDto);
            redirectAttributes.addFlashAttribute("alreadyInUse", true);
            return "redirect:/users/register";
        }


        return "redirect:/login";
    }

}
