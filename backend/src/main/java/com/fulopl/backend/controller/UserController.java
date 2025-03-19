package com.fulopl.backend.controller;

import com.fulopl.backend.model.entity.AppUser;
import com.fulopl.backend.model.payload.JwtResponse;
import com.fulopl.backend.model.payload.UserCredentials;
import com.fulopl.backend.model.payload.UserDataResponse;
import com.fulopl.backend.repository.RoleRepository;
import com.fulopl.backend.repository.UserRepository;
import com.fulopl.backend.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public void createUser(@RequestBody UserCredentials userCredentials) throws IllegalArgumentException {
        if (userRepository.existsByEmail(userCredentials.email()))
            throw new IllegalArgumentException("Username not available.");

        AppUser appUser = new AppUser();
        appUser.setEmail(userCredentials.email());
        appUser.setPassword(passwordEncoder.encode(userCredentials.password()));
        appUser.setRoles(Set.of(roleRepository.findByName("ROLE_USER")));
        userRepository.save(appUser);
    }

    @PostMapping("/login")
    public JwtResponse authenticateUser(@RequestBody UserCredentials userCredentials) {
        System.out.println("Login initiated");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentials.email(), userCredentials.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new JwtResponse(jwtUtils.generateJwtToken(authentication));
    }

    @GetMapping("/me")
    public UserDataResponse getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User userDetails = (User) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        return new UserDataResponse(userDetails.getUsername(), roles);
    }
}
