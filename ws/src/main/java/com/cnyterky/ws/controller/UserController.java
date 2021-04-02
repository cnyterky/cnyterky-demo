package com.cnyterky.ws.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnyterky.ws.common.ApiError;
import com.cnyterky.ws.model.User;
import com.cnyterky.ws.service.UserService;

@RestController
@RequestMapping("/api/1.0")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
	ApiError apiError = new ApiError(400, "Validation Error", "/api/1.0/users");
	Map<String, String> validationErrors = new HashMap<String, String>();
	String userName = user.getUserName();
	String fullName = user.getFullName();
	if (userName == null || userName.isEmpty()) {
	    validationErrors.put("userName", "Username cannot be null");
	}

	if (fullName == null || fullName.isEmpty()) {
	    validationErrors.put("fullName", "Cannot be null");
	}
	if (validationErrors.size() > 0) {
	    apiError.setValidationErrors(validationErrors);
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	userService.save(user);
	log.info("user created");
	return ResponseEntity.ok().build();
    }

}
