package com.fulopl.backend.service;

import com.fulopl.backend.model.entity.AppUser;
import com.fulopl.backend.model.payload.UserDataResponse;
import com.fulopl.backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDataResponse getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User userDetails = (User) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        return new UserDataResponse(userDetails.getUsername(), roles);
    }

    public AppUser getUser() {
        String email = getUserData().email();
        return userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email));
    }
}
