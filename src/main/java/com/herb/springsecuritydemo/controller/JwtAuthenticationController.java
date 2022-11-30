package com.herb.springsecuritydemo.controller;

import com.herb.springsecuritydemo.config.JwtTokenUtil;
import com.herb.springsecuritydemo.dto.request.JwtRequest;
import com.herb.springsecuritydemo.dto.request.UserRequestDTO;
import com.herb.springsecuritydemo.dto.response.JwtResponse;
import com.herb.springsecuritydemo.service.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final AuthenticationManager manager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest request) throws Exception {
        Authentication authenticate = authenticate(request.getUsername(), request.getPassword());

        String token = jwtTokenUtil.generateToken(authenticate);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(userDetailsService.addUser(user));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userDetailsService.getAllUsers());
    }

    private Authentication authenticate(String username, String password) throws Exception {
        Authentication authenticate;
        try {
             authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        return authenticate;
    }
}
