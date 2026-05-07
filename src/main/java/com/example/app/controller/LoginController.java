package com.example.app.controller;

import com.example.app.model.*;
import com.example.app.dto.LoginDTO;
import com.example.app.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {

        model.addAttribute("loginDTO", new LoginDTO());

        return "login";
    }

    @PostMapping("/login")
    public String loginUser(
            @ModelAttribute LoginDTO loginDTO,
            HttpSession session,
            Model model) {

        Optional<User> optionalUser =
                userRepository.findByEmail(loginDTO.getEmail());

        if(optionalUser.isEmpty()){

            model.addAttribute("error", "Email not found");

            return "login";
        }

        User user = optionalUser.get();

        if(!user.getPassword().equals(loginDTO.getPassword())){

            model.addAttribute("error", "Wrong password");

            return "login";
        }

        session.setAttribute("loggedUser", user);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){

        session.invalidate();

        return "redirect:/login";
    }
}