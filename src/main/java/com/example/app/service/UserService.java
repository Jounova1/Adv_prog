package com.example.app.service;

import com.example.app.dto.UserDTO;
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
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get user by ID
     */
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    /**
     * Get user by email
     */
    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    /**
     * Create a new user
     */
    public UserDTO createUser(UserDTO userDTO) {
        // Check if email already exists
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists: " + userDTO.getEmail());
        }

        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    /**
     * Update an existing user
     */
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        user.setIsActive(userDTO.getIsActive());

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
    public List<UserDTO> searchUsers(String searchTerm) {
        return userRepository.searchUsers(searchTerm)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get active users
     */
    public List<UserDTO> getActiveUsers() {
        return userRepository.findByIsActive(true)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convert Entity to DTO
     */
    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getIsActive()
        );
    }

    /**
     * Convert DTO to Entity
     */
    private User convertToEntity(UserDTO dto) {
        return new User(
                null,
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getIsActive()
        );
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
