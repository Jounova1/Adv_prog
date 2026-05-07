package com.example.app.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.app.model.Users;

@Controller
public class SignUpcontroller {

    @Autowired
    private CrudRepository<Users, Long> userRepo;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new Users());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Users user, HttpSession session) {
        userRepo.save(user);
        session.setAttribute("loggedUser", user);
        return "redirect:/home";
    }
}