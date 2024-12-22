package com.gamehouse.project.Models.DTO;

import com.gamehouse.project.Models.AbstractEntity;
import com.gamehouse.project.Models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginForm  {

    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 3, max = 12, message = "Keep between 3 to 12 characters.")
    private String password;

public LoginForm(String username, String password){
    this.username=username;
    this.password=password;
}
public LoginForm(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
