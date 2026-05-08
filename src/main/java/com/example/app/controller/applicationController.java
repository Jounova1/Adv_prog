package com.example.app.controller;

import com.example.app.model.Application;
import com.example.app.service.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
public class applicationController {

    private final ApplicationService service;

    public applicationController(ApplicationService service) {
        this.service = service;
    }

    // =========================
    // PAGE (THYMELEAF)
    // =========================
    @GetMapping("/apply")
    public String applyPage(@RequestParam Long jobId, Model model) {
        model.addAttribute("jobId", jobId);
        return "Application"; // templates/Application.html
    }

    // =========================
    // API (JSON)
    // =========================
    @PostMapping("/applications")
    @ResponseBody
    public Application apply(@RequestBody Application app) {
        return service.apply(app);
    }
}