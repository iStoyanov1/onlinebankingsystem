package com.example.onlinebankingsystem.data.models.binding;

import javax.validation.constraints.Pattern;

public class UserEditEmailBindingModel {

    private String username;
    private String email;
    private String newEmail;

    public UserEditEmailBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = "^([\\w\\.\\-]+)@([\\w\\-]+)(\\D(\\.(\\w){2,3})+)$", message = "Неправилен имейл адрес")
    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
