package com.cnyterky.ws.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cnyterky.ws.model.User;
import com.cnyterky.ws.repository.UserRepository;
import com.cnyterky.ws.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
	this.userRepository = userRepository;
	this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User save(@RequestBody User user) {
	userRepository.save(user);
	String encryptedPassword = passwordEncoder.encode(user.getPassword());
	user.setPassword(encryptedPassword);
	log.info("user created : " + user.getId());
	return user;
    }

}
