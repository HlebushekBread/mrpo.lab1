package org.mrpo.lab1.security;

import org.mrpo.lab1.models.Role;
import org.mrpo.lab1.models.User;
import org.mrpo.lab1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " not found!");
        }
        return new UserDetailsImpl(user.get());
    }

    public UserDetailsImpl loadGuestUser() {
        User user = new User(0, new Role(1, "Гость"), "Гость", "guest", "guest");
        return new UserDetailsImpl(user);
    }
}
