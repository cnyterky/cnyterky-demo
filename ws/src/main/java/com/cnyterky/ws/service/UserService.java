package com.cnyterky.ws.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cnyterky.ws.model.User;

@Service
public interface UserService {
    public User save(@RequestBody User user);
}
