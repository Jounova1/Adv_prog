package com.example.app.controller;

import com.example.app.model.Job;
import com.example.app.model.Job;
import com.example.app.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@CrossOrigin("*")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }
}