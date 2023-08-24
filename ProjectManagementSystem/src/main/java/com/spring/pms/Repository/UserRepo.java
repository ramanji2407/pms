package com.spring.pms.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.pms.Entity.Task;
import com.spring.pms.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	Optional<User> findByName(String username);
    public User findById(int id);



}
