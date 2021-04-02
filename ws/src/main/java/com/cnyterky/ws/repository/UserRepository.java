package com.cnyterky.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cnyterky.ws.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
