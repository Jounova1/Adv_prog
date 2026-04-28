package com.example.app.controller;

import com.example.app.dto.UserDTO;
import com.example.app.repository.UserRepository;
import com.example.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * GET - Retrieve all users
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * GET - Retrieve user by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * GET - Search users by term
     */
    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam String term) {
        List<UserDTO> users = userService.searchUsers(term);
        return ResponseEntity.ok(users);
    }

    /**
     * GET - Retrieve active users
     */
    @GetMapping("/active/list")
    public ResponseEntity<List<UserDTO>> getActiveUsers() {
        List<UserDTO> users = userService.getActiveUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * POST - Create a new user
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User created successfully");
        response.put("user", createdUser);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * PUT - Update an existing user
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User updated successfully");
        response.put("user", updatedUser);
        
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE - Delete a user by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        
        return ResponseEntity.ok(response);
    }

    /**
     * Exception handler for RuntimeException
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, HttpSession session) {

        userRepo.save(user);

        // نحفظ المستخدم في session
        session.setAttribute("loggedUser", user);

        return "redirect:/home";
    }
}
}
