package com.example.app.controller;

import com.example.app.model.*;
import com.example.app.service.JobService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public String showJobs(Model model) {
        model.addAttribute("jobs", jobService.getAllJobs());
        return "jobs";
    }

    @GetMapping("/job/{id}")
    public String jobDetails(@PathVariable Long id, Model model) {

        JobPost job = jobService.getJobById(id);

        model.addAttribute("job", job);

        return "job-details";
    }

    @GetMapping("/job/create")
    public String showCreateForm(Model model, HttpSession session) {

        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("job", new JobPost());
        return "create-job";
    }

    @PostMapping("/job/create")
    public String createJob(@ModelAttribute JobPost job, HttpSession session) {

        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        jobService.createJob(job);
        return "redirect:/jobs";
    }

    @GetMapping("/job/delete/{id}")
    public String deleteJob(@PathVariable Long id, HttpSession session) {

        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        jobService.deleteJob(id);
        return "redirect:/jobs";
    }
}