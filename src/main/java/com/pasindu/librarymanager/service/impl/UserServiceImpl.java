package com.pasindu.librarymanager.service.impl;

import com.pasindu.librarymanager.entity.User;
import com.pasindu.librarymanager.enumeration.RoleEnum;
import com.pasindu.librarymanager.repository.UserRepository;
import com.pasindu.librarymanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(RoleEnum.ROLE_ADMIN));
        LOG.info("New user created, username: {}", user.getUsername());
        return userRepository.save(user);
    }
}
