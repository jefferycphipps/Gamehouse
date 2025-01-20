package com.gamehouse.project.models.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterForm extends LoginForm {

    @NotEmpty(message = "Email is required.")
    @Email(message = "Has to be a valid email.")
    private String email;

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 100, message = "You need a minimum of 3 characters.")
    private String name;

    @NotBlank(message = "Verify password is required.")
    private String verifyPassword;
    private String recaptcha;

    @Override
    public String getRecaptcha() {
        return recaptcha;
    }

    @Override
    public void setRecaptcha(String recaptcha) {
        this.recaptcha = recaptcha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
