package com.example.onlinebankingsystem.data.models.binding;

import javax.validation.constraints.Pattern;

public class UserEditPhoneBindingModel {

    private String username;
    private String phone;

    public UserEditPhoneBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Pattern(regexp = "\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})", message = "Въведеният номер е неправилен")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
