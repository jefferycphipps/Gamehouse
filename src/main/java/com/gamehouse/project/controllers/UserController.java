package com.gamehouse.project.controllers;

import com.gamehouse.project.Models.DTO.LoginForm;
import com.gamehouse.project.Models.DTO.RegisterForm;
import com.gamehouse.project.Models.User;
import com.gamehouse.project.Models.data.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }
        return user.orElse(null);
    }
    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());

    }

    @GetMapping("/register")
    public ResponseEntity<RegisterForm> getRegisterForm() {
    RegisterForm form = new RegisterForm();
    return ResponseEntity.ok(form);
    }

    @PostMapping("/register")
    public ResponseEntity<String> processRegisterFrom (@RequestBody @Valid RegisterForm registerFormDTO,
                                                       HttpServletRequest request) {
        if (userRepository.findByName(registerFormDTO.getName()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Name already exists.");
        }

        if (userRepository.findByUsername(registerFormDTO.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists.");
        }

        if (userRepository.findByEmail(registerFormDTO.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists.");
        }

        if (!registerFormDTO.getPassword().equals(registerFormDTO.getVerifyPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match.");
        }
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Register");
//            return "register";
//        }
//
//        User existingName = userRepository.findByName(registerFormDTO.getName());
//        if (existingName != null) {
//            errors.rejectValue("name", "name.alreadyexists", "name cannot be empty");
//            model.addAttribute("title", "Register");
//            return "register";
//        }
//
//
//        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());
//        if (existingUser != null) {
//            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
//            model.addAttribute("title", "Register");
//            return "register";
//        }
//
//        User existingEmail = userRepository.findByEmail(registerFormDTO.getEmail());
//        if (existingEmail != null) {
//            errors.rejectValue("email", "email.alreadyexists", "A user with this email already exists.");
//            model.addAttribute("title", "Register");
//            return "register";
//        }
//
//
//        if (registerFormDTO.getPassword() == null || registerFormDTO.getPassword().trim().isEmpty()) {
//            errors.rejectValue("password", "password.empty", "Password cannot be empty");
//            model.addAttribute("title", "Register");
//            return "register";
//        }
//
//        if (!registerFormDTO.getPassword().equals(registerFormDTO.getVerifyPassword())) {
//            errors.rejectValue("verifyPassword", "passwords.mismatch", "Passwords do not match");
//            model.addAttribute("title", "Register");
//            return "register";
//        }

        BCryptPasswordEncoder passwordEncode = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncode.encode(registerFormDTO.getPassword());

        User newUser = new User();
        newUser.setName(registerFormDTO.getName());
        newUser.setUsername(registerFormDTO.getUsername());
        newUser.setEmail(registerFormDTO.getEmail());
        newUser.setPwHash(encodedPassword);

        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);


        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }
    @GetMapping("/login")
    public ResponseEntity<LoginForm> getLoginForm() {
        LoginForm form = new LoginForm();
        return ResponseEntity.ok(form);
    }

    @PostMapping("/login")
    public ResponseEntity<String> processLoginForm(@RequestBody @Valid LoginForm loginFormDTO, HttpServletRequest request){

        User user = userRepository.findByName(loginFormDTO.getUsername());

        if (user == null || !user.isMatchingPassword(loginFormDTO.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Login");
//            return "login";
//        }
//
//        User users = userRepository.findByUsername(loginFormDTO.getUsername());
//        if (users == null) {
//            errors.rejectValue("username", "username.alreadyexists", "Username is invalid");
//            model.addAttribute("title", "Login");
//            return "login";
//        }
//
//        String password = loginFormDTO.getPassword();
//
//        if (!users.isMatchingPassword(password)) {
//            errors.rejectValue("password", "password.invalid", "Invalid password");
//            model.addAttribute("title", "Log In");
//            return "login";
//        }
        setUserInSession(request.getSession(), user);


//        if (session != null) {
//            System.out.println("Session is active. Session ID: " + session.getId());
//        } else {
//            System.out.println("No active session.");
//        }
        return ResponseEntity.ok("Login successful.");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        request.getSession().invalidate();

        return ResponseEntity.ok("Logged out successfully.");

    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<String> Displayuser(@PathVariable String username, HttpSession session){
        User loginuser = getUserFromSession(session);
//        if (user == null) {
//            return "redirect:/login";
//        }
//        model.addAttribute("title", "Dashboard");
//        model.addAttribute("user", user);
//        return "user"
        if (loginuser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You need to login.");
        }
        if (!loginuser.getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not allowed to see these details.");
        }
        return ResponseEntity.ok("Hello," + loginuser.getUsername());
    }

}
