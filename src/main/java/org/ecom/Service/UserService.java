package org.ecom.Service;

import org.ecom.Model.User;
import org.ecom.Model.UserRegistrationDTO;
import org.ecom.Repository.UserRepository;
import org.ecom.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordService passwordService;

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public void createUser(UserRegistrationDTO userDTO) {
        Utils.validateUserRegistrationDTO(userDTO);
        User user = userDTO.createUserFromUserRegistrationDTO();
        user.setPassword(passwordService.encodePassword(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
