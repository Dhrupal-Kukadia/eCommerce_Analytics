package org.ecom.DTOs;

import org.ecom.Model.User;

public class UserRegistrationDTO {
    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User createUserFromUserRegistrationDTO() {
        User user = new User();
        user.setEmail(this.getEmail());
        user.setName(this.getName());
        user.setPassword(this.getPassword());
        return user;
    }
}
