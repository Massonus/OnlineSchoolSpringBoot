package com.massonus.onlineschoolspringboot.service;

import com.massonus.onlineschoolspringboot.entity.Role;
import com.massonus.onlineschoolspringboot.entity.User;
import com.massonus.onlineschoolspringboot.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean addUser(final User user, final boolean isAdmin) {
        User userFromDb = userRepo.findUserByName(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        if (isAdmin) {
            user.setRoles(Collections.singleton(Role.ADMIN));
        } else {
            user.setRoles(Collections.singleton(Role.USER));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        return true;
    }

}
