package com.example.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD
import com.example.app.model.Users;
=======

import com.example.app.model.*;
>>>>>>> 69c8fc6a3963407f60fd4bcbdf9f22fe93cfb2aa

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

   
}