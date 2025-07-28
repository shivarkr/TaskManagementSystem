package com.taskmanager.app.securityconfigs.service;



import com.taskmanager.app.entitys.User;
import com.taskmanager.app.repositry.UserRepo;
import com.taskmanager.app.securityconfigs.entities.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUserName(username);
        if(user==null){
            System.out.println("user not found  404 ");
            throw  new UsernameNotFoundException("user not found 404 ");
        }
        return new UserPrincipal(user);
    }
}
