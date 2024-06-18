package org.ecom.Website.Controller;

import org.ecom.Website.DTO.AuthDTO;
import org.ecom.Website.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody AuthDTO authDTO){
        final String userId =authDTO.getUserId();
        final String password=authDTO.getPassword();
        boolean success = loginService.authenticateUser(userId,password);
        if(success){
            //set userContext
            return ResponseEntity.status(HttpStatus.OK).body("Login Successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }
}
