package com.pasindu.librarymanager.service.impl;

import com.pasindu.librarymanager.exception.AuthenticationException;
import com.pasindu.librarymanager.service.AuthService;
import com.pasindu.librarymanager.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String authenticate(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            if (authentication.isAuthenticated()) {
                return jwtUtil.generateToken(username);
            }
        } catch (AuthenticationException e) {
            throw new AuthenticationException("Invalid credentials");
        }
        return jwtUtil.generateToken(username);
    }
}
