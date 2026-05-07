package com.example.app.service;

import com.example.app.model.JobPost;
import com.example.app.repository.JobPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobPostRepository jobRepo;

    public JobService(JobPostRepository jobRepo) {
        this.jobRepo = jobRepo;
    }

    // Get all jobs
    public List<JobPost> getAllJobs() {
        return jobRepo.findAll();
    }

    // Get one job by id
    public JobPost getJobById(Long id) {

        return jobRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    // Create new job
    public void createJob(JobPost job) {
        jobRepo.save(job);
    }

    // Delete job
    public void deleteJob(Long id) {
        jobRepo.deleteById(id);
    }
}