package com.example.app.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {

        
        Object user = session.getAttribute("loggedUser");
        if(user == null){
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        return "home"; 
    }

}