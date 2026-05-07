package com.example.app.controller;
import com.example.app.model.*;
import com.example.app.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {
    private UserService userService;
    public ProfileController(UserService userService){
        this.userService=userService;
    }
    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("loggedUser");
        if(user == null){
            return "redirect:/login";
        }
        model.addAttribute("user",user);
        return "profile";
    }
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute Users updateUser, HttpSession session) {
        Users user = (Users) session.getAttribute("loggedUser");
        if(user == null){
            return "redirect:/login";
        }
        Users savedUser = userService.updateProfile(user.getId(), updateUser);
        session.setAttribute("loggedUser", savedUser);
        return "redirect:/profile";
    }
    
    
}