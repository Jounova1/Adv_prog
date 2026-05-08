package com.example.app.service;

import com.example.app.dto.SignupRequest;
import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * Get all users
     */
    public List<SignupRequest> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get user by ID
     */
    public SignupRequest getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    /**
     * Get user by email
     */
    public SignupRequest getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }
        return convertToDTO(user);
    }

    /**
     * Create a new user
     */
    public SignupRequest createUser(SignupRequest userDTO) {
        // Check if email already exists
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new RuntimeException("Email already exists: " + userDTO.getEmail());
        }

        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    /**
     * Update an existing user
     */
    public SignupRequest updateUser(Long id, SignupRequest userDTO) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        user.setActive(userDTO.getIsActive() != null ? userDTO.getIsActive() : user.isActive());

        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    /**
     * Delete a user by ID
     */
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    /**
     * Search users by term
     */
    public List<SignupRequest> searchUsers(String searchTerm) {
        return userRepository.findByEmailContaining(searchTerm)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get active users
     */
    public List<SignupRequest> getActiveUsers() {
        return userRepository.findByActive(true)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    /**
     * Convert Entity to DTO
     */
    private SignupRequest convertToDTO(User user) {
        return new SignupRequest(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.isActive()
        );
    }

    /**
     * Convert DTO to Entity
     */
    private User convertToEntity(SignupRequest dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setActive(dto.getIsActive() != null ? dto.getIsActive() : false);
        return user;
    }

    public User updateProfile(Long id, User updatedUser){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPhone(updatedUser.getPhone());
        user.setQualification(updatedUser.getQualification());
        user.setExperience(updatedUser.getExperience());
        user.setAge(updatedUser.getAge());
        return userRepository.save(user);
    }
}
