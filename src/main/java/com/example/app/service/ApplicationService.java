package com.example.app.service;

import com.example.app.model.Application;
import com.example.app.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final ApplicationRepository repo;

    public ApplicationService(ApplicationRepository repo) {
        this.repo = repo;
    }

    public Application apply(Application app) {
        app.setStatus("PENDING");
        return repo.save(app);
    }
}