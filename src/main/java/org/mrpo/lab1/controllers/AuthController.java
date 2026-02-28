package org.mrpo.lab1.controllers;

import org.mrpo.lab1.dtos.JwtRequest;
import org.mrpo.lab1.dtos.JwtResponse;
import org.mrpo.lab1.exceptions.AppException;
import org.mrpo.lab1.security.UserDetailsImpl;
import org.mrpo.lab1.security.UserDetailsServiceImpl;
import org.mrpo.lab1.security.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserDetailsServiceImpl userDetailsService, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        UserDetailsImpl userDetails;
        try {
            if(authRequest.getUsername().equals("guest") && authRequest.getPassword().equals("guest")) {
                userDetails = userDetailsService.loadGuestUser();
            } else {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
                userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            }
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppException(HttpStatus.UNAUTHORIZED.value(), "Incorrect credentials"), HttpStatus.UNAUTHORIZED);
        }
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
