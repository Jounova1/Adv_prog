package com.example.app.repository;

import com.example.app.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface jobpostRepository extends JpaRepository<JobPost, Long> {
  
}
