package com.example.onlinebankingsystem.web.view.models;

public class UserViewModel {

    private String fullName;
    private String email;

    public UserViewModel() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
