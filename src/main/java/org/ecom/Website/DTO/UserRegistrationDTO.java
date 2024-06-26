package org.ecom.Website.DTO;

import org.ecom.Website.Model.Address;
import org.ecom.Website.Model.User;

public class UserRegistrationDTO {
    private String name;
    private String email;
    private String password;
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User createUserFromUserRegistrationDTO() {
        User user = new User();
        user.setEmail(this.getEmail());
        user.setName(this.getName());
        user.setPassword(this.getPassword());
        user.setAddress(this.address);
        return user;
    }
}
