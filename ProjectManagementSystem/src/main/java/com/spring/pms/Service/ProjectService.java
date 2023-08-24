package com.spring.pms.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

@Service
public class ProjectService {
	@Autowired 
	private ProjectRepo projectRepo;
	
//	@Autowired
//	private PasswordEncoder encoder;
	
	public List<Project>getAllProjects()
	{
		return projectRepo.findAll();
	}
	public Project getAllProject( int id)
	{
		return projectRepo.findById(id);
	}
	
	public void postProject( Project project)
	{
		Set<User> user = project.getUser();
		
	    //user.forEach(users -> users.setPassword(encoder.encode(users.getPassword())));
		projectRepo.save(project);
		
	}
	
	public Project updateProject( Project project ,   int id)
	{
		Project projectupdate=projectRepo.findById(id);
		projectupdate.setDeadlineDate(project.getDeadlineDate());
		projectupdate.setDescription(project.getDescription());
		projectupdate.setName(project.getName());
		projectupdate.setId(project.getId());
		projectupdate.setStatus(project.getStatus());
		projectupdate.setStartDate(project.getStartDate());
		projectRepo.save(projectupdate);
		
		return projectupdate;
	}
	
	public void deleteProject(  int id)
	{
		projectRepo.deleteById(id);
	}
	

}
