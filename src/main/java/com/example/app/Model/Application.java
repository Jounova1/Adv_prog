package com.example.worklink.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private Long id;
    private Long userId;
    private Long jobId;
    private String status; 
    
}