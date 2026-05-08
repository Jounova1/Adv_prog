package com.example.app.service;

import com.example.app.dto.LoginRequest;
import com.example.app.dto.SignupRequest;
import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ SIGNUP
  public User signup(SignupRequest request) {

    if (userRepository.findByEmail(request.getEmail()) != null) {
        throw new RuntimeException("Email already exists");
    }

    User user = new User();
    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());
    user.setPhone(request.getPhone());
    user.setRole(request.getRole()); 
    user.setActive(true);

    return userRepository.save(user);
}

    
   public User login(LoginRequest request) {

    User user = userRepository.findByEmail(request.getEmail());

    if (user == null) {
        throw new RuntimeException("User not found");
    }

    if (!user.getPassword().equals(request.getPassword())) {
        throw new RuntimeException("Wrong password");
    }

    return user;
}
}