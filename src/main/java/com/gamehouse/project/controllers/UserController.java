package com.gamehouse.project.controllers;

import com.gamehouse.project.models.Image;
import com.gamehouse.project.models.RecaptchaService;
import com.gamehouse.project.models.data.ImageRepository;
import com.gamehouse.project.models.dto.LoginForm;
import com.gamehouse.project.models.dto.RegisterForm;
import com.gamehouse.project.models.User;
import com.gamehouse.project.models.data.UserRepository;
import com.gamehouse.project.services.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173/", allowCredentials = "true")
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private RecaptchaService recaptchaService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;

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
    public ResponseEntity<String> processRegisterFrom(@RequestBody @Valid RegisterForm registerFormDTO, HttpServletRequest request) {
        String recaptchaToken = registerFormDTO.getRecaptcha();
        String secretKey = "6LeD3a8qAAAAAAa9-p7i-_CrWgLMybLkRx22nimk";

        if (!recaptchaService.verifyRecaptcha(recaptchaToken, secretKey)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed reCAPTCHA verification.");
        }
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
    public ResponseEntity<String> processLoginForm(@RequestBody @Valid LoginForm loginFormDTO, HttpServletRequest request) {
        String recaptchaToken = loginFormDTO.getRecaptcha();

        String secretKey = "6LeD3a8qAAAAAAa9-p7i-_CrWgLMybLkRx22nimk";

        if (!recaptchaService.verifyRecaptcha(recaptchaToken, secretKey)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed reCAPTCHA verification.");
        }

        User user = userRepository.findByUsername(loginFormDTO.getUsername());

        if (user == null || !user.isMatchingPassword(loginFormDTO.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }

        setUserInSession(request.getSession(), user);

        return ResponseEntity.ok("Login successful.");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        request.getSession().invalidate();

        return ResponseEntity.ok("Logged out successfully.");
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<String> Displayuser(@PathVariable String username, HttpSession session) {
        User loginuser = getUserFromSession(session);

        if (loginuser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You need to login.");
        }
        if (!loginuser.getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not allowed to see these details.");
        }
        return ResponseEntity.ok("Hello," + loginuser.getUsername());
    }

    @PostMapping("/getUser")
    public User getUser(@RequestBody String username) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            return null;
        }
        return user;
    }

    @PostMapping("/saveUserImage")
    public ResponseEntity<?>saveUserImage(@RequestParam("user") String username, @RequestParam("image") MultipartFile file) throws IOException {
        User user = userRepository.findByUsername(username);
        Image temp = imageRepository.findByName(file.getOriginalFilename());
        if(temp !=null){
            user.setProfileImage(temp);
            userRepository.save(user);
            return ResponseEntity.ok("Image already in system and saved to user!");
        }
        int respsone = imageService.uploadImageToFileDirectory(file);
        if(respsone!=-1){
            Optional<Image> newImage = imageRepository.findById(respsone);
            if(newImage.isPresent()) {
                Image userImage = newImage.get();
                user.setProfileImage(userImage);
                userRepository.save(user);
            }
        }

        return ResponseEntity.ok("Success!");
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?>deleteUser(@PathVariable String username){
        User deletedUser = userRepository.findByUsername(username);
        if(deletedUser!=null){
            userRepository.delete(deletedUser);
            return ResponseEntity.ok("User:"+username+" deleted!");
        }
        return ResponseEntity.ok("user not found");
    }

}