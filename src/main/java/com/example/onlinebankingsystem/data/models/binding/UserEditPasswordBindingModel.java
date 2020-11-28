package com.example.onlinebankingsystem.data.models.binding;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserEditPasswordBindingModel {

    private String username;
    private String oldPassword;
    private String password;
    private String confirmPassword;

    public UserEditPasswordBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    //"Паролата трябва да съдържа: \nМинимум 8 символа.\n " +
    //                    "Минимум една малка буква.\n Минимум една главна буква. \n" +
    //                    "Поне една цифра.\n Поне един специален символ(@, #, % и др.) "

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "Паролата трябва да съдържа: \nМинимум 8 символа.\n " +
            "Минимум една малка буква.\n Минимум една главна буква. \n" +
            "Поне една цифра.\n Поне един специален символ(@, #, % и др.) ")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
