package com.example.app.controller;

<<<<<<< HEAD
import com.example.app.model.Users;
=======
import com.example.app.model.*;
import com.example.app.repository.UserRepository;
>>>>>>> 69c8fc6a3963407f60fd4bcbdf9f22fe93cfb2aa
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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