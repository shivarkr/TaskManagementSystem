package com.taskmanager.app.controllers;



import com.taskmanager.app.entitys.User;
import com.taskmanager.app.enums.Role;
import com.taskmanager.app.repositry.UserRepo;
import com.taskmanager.app.securityconfigs.service.JwtService;
import com.taskmanager.app.securityconfigs.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepo u;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;
    @PostMapping("register")
    public void addUser(@RequestBody User user){
        user.setUserPassword(new BCryptPasswordEncoder(10).encode(user.getUserPassword()));
        user.setRole(Role.ADMIN);
        u.save(user);
    }
    @PostMapping("login")
    public String login(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getUserPassword()));
        if(authentication.isAuthenticated()){
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getUserName());
            return jwtService.generateToken(userDetails);
        }else{
            return "Login failed";
        }
    }
}
