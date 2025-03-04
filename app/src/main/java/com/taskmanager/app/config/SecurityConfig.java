package com.taskmanager.app.config;

import com.taskmanager.app.entity.User;
import com.taskmanager.app.enums.Role;
import com.taskmanager.app.repositry.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.Map;

@Configuration
public class SecurityConfig {

    @Autowired
    UserRepo userRepo;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/user/**","/tasks/**").hasRole("ADMIN")
                        .requestMatchers("/assigned/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .userService(oAuth2UserService())
                        )
                );
        return http.build();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

        return request -> {
            OAuth2User oAuth2User = delegate.loadUser(request);
            Map<String, Object> attributes = oAuth2User.getAttributes();
            String email = (String) attributes.get("email");

            User user = userRepo.findByUserName(email).orElseGet(() -> {
                User newUser = new User();
                newUser.setUserName(email);
                newUser.setRole(Role.USER);
                return userRepo.save(newUser);
            });

            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole())),
                    attributes,
                    "name"
            );
        };

    }
}