package com.example.app.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
}