package com.spring.pms.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.pms.Entity.Project;
import com.spring.pms.Entity.User;
import com.spring.pms.Repository.ProjectRepo;
import com.spring.pms.Repository.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ProjectRepo projectRepo;
	@Autowired

	private PasswordEncoder encoder;
	public List<User>getAllUsers()
	{
		return userRepo.findAll();
	}
	public User getAllUser( int id)
	{
		return userRepo.findById(id);
	}
	
	public void postUser( User user,  int id)
	{
		Project project=projectRepo.findById(id);
	user.setPassword(encoder.encode(user.getPassword()));
		project.getUser().add(user);
		projectRepo.save(project);
		//userRepo.save(user);
		
	
	}
	public User updateUser( User user ,   int id)
	{
		User user1=userRepo.findById(id);
		user1.setDepartment(user.getDepartment());
		user1.setId(user.getId());
		user1.setEmail(user.getEmail());
		user1.setPassword(encoder.encode(user.getPassword()));
		//user1.setPassword(user.getPassword());
		user1.setRole(user.getRole());
		userRepo.save(user1);

		
		return user1;
	}
	
	public void deleteUser(  int id)
	{
		userRepo.deleteById(id);
	}


}
