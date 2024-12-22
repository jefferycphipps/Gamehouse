package com.gamehouse.project.controllers;

import com.gamehouse.project.Models.DTO.LoginForm;
import com.gamehouse.project.Models.DTO.RegisterForm;
import com.gamehouse.project.Models.User;
import com.gamehouse.project.Models.data.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;


@Controller
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
    public String displayform(Model model) {
        model.addAttribute("registerFormDTO", new RegisterForm());
        model.addAttribute("title", "Resister");

        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute("registerFormDTO") @Valid RegisterForm registerFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }

        User existingName = userRepository.findByName(registerFormDTO.getName());
        if (existingName != null) {
            errors.rejectValue("name", "name.alreadyexists", "name cannot be empty");
            model.addAttribute("title", "Register");
            return "register";
        }


        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());
        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        User existingEmail = userRepository.findByEmail(registerFormDTO.getEmail());
        if (existingEmail != null) {
            errors.rejectValue("email", "email.alreadyexists", "A user with this email already exists.");
            model.addAttribute("title", "Register");
            return "register";
        }


        if (registerFormDTO.getPassword() == null || registerFormDTO.getPassword().trim().isEmpty()) {
            errors.rejectValue("password", "password.empty", "Password cannot be empty");
            model.addAttribute("title", "Register");
            return "register";
        }

        if (!registerFormDTO.getPassword().equals(registerFormDTO.getVerifyPassword())) {
            errors.rejectValue("verifyPassword", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }

        BCryptPasswordEncoder passwordEncode = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncode.encode(registerFormDTO.getPassword());

        User newUser = new User();
        newUser.setName(registerFormDTO.getName());
        newUser.setUsername(registerFormDTO.getUsername());
        newUser.setEmail(registerFormDTO.getEmail());
        newUser.setPwHash(encodedPassword);

        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);


        return "index";
    }

    @GetMapping("/login")
    public String displayLogin(Model model) {
        model.addAttribute("loginFormDTO", new LoginForm());
        model.addAttribute("title", "Login");


        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute("loginFormDTO") @Valid LoginForm loginFormDTO, Errors errors,Model model, HttpServletRequest request){

        if (errors.hasErrors()) {
            model.addAttribute("title", "Login");
            return "login";
        }

        User users = userRepository.findByUsername(loginFormDTO.getUsername());
        if (users == null) {
            errors.rejectValue("username", "username.alreadyexists", "username is invalid");
            model.addAttribute("title", "Login");
            return "login";
        }

        String password = loginFormDTO.getPassword();

        if (!users.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }
        setUserInSession(request.getSession(), users);

        HttpSession session = request.getSession(false); // Use false to avoid creating a new session if it doesn't exist
        if (session != null) {
            System.out.println("Session is active. Session ID: " + session.getId());
        } else {
            System.out.println("No active session.");
        }
        return "redirect:/user";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        HttpSession session = request.getSession(false);

        if (session != null) {
            System.out.println("Logging out. Session ID: " + session.getId());
        } else {
            System.out.println("No active session to log out.");
        }
        return "redirect:/login";

    }

    @GetMapping("/user")
    public String Displayuser(HttpSession session, Model model){
        User user = getUserFromSession(session);
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Dashboard");
        model.addAttribute("user", user);
        return "user";
    }

}
