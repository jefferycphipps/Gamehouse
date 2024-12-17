package com.gamehouse.project.controllers;

import com.gamehouse.project.Models.DTO.RegisterForm;
import com.gamehouse.project.Models.User;
import com.gamehouse.project.Models.data.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    Optional <User> user = userRepository.findById(userId);

    if(user.isEmpty()){
        return null;
    }
    return user.get();
}
private static void setUserSession(HttpSession session, User user) {
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


        User existingUser = userRepository.findByUsername(registerFormDTO.getUserName());
        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }


        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("verifyPassword", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }


        User newUser = new User(registerFormDTO.getUserName(), registerFormDTO.getPassword());
        userRepository.save(newUser);
        setUserSession(request.getSession(), newUser);


        return "index";
    }





}
