package com.gamehouse.project.Models.DTO;

import com.gamehouse.project.Models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterForm extends LoginForm {

    private String email;

    private String name;

    @NotBlank(message = "Please confirm your password")
    private String verifyPassword;

//    public RegisterForm(String verifyPassword, String name, String email){
//        super();
//        this.verifyPassword=verifyPassword;
//        this.name=name;
//        this.email=email;
//    }

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
