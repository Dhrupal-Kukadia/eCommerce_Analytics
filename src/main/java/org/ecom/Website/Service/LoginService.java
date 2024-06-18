package org.ecom.Website.Service;

import org.ecom.Website.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordService passwordService;

    public Boolean authenticateUser(String userId, String password) {
        final User user = userService.getUserById(userId);
        final String hashedPassword = user.getPassword();
        return passwordService.matchPassword(password, hashedPassword);
    }
}
