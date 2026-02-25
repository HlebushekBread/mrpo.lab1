package org.mrpo.lab1.security;


import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationProviderImpl(UserDetailsServiceImpl userDetailsServiceImpl, PasswordEncoder passwordEncoder) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetailsImpl userDetails;
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        if(username.equals("guest") && password.equals("guest")) {
            userDetails = userDetailsServiceImpl.loadGuestUser();
        } else {
            userDetails = userDetailsServiceImpl.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("Incorrect credentials!");
            }
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}