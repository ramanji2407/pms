package com.spring.pms.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.pms.Entity.Project;
import com.spring.pms.Entity.Subtask;
import com.spring.pms.Entity.Task;
import com.spring.pms.Repository.ProjectRepo;
import com.spring.pms.Repository.TaskRepository;
import com.spring.pms.Repository.UserRepo;
import com.spring.pms.Entity.User;
import com.spring.pms.Exceptions.BadRequestException;
import com.spring.pms.Exceptions.DetailsNotFoundException;
@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	@Autowired 
	private ProjectRepo projectRepository;
	@Autowired
	private UserRepo userRepo;
	public List<Task>getAllTasks()
	{
		return taskRepository.findAll();
	}
	public Task getAllTask( long id)
	{
		return taskRepository.findById(id);
	}
	
	public void postTask( Task task,  int id, int pid)
	{
		Project project=projectRepository.findById(pid);
		if(project.getStatus().equals("Inactive"))
		{
			throw new BadRequestException("Cannot_create_task_beacuse_Project was_Inactive");
		}
		project.getTasks().add(task);
		projectRepository.save(project);
		User user=userRepo.findById(id);
		user.getTasks().add(task);
		userRepo.save(user);
		taskRepository.save(task);
	}
	public Task updateTask( Task task ,   long id)
	{
		Task task1=taskRepository.findById(id);
		List<Subtask> subtask = task1.getSubtask();
		String status=task.getStatus();
	
		for(Subtask subtask2:subtask)
		{
			
			if(subtask2.getStatus().equals("InProgress"))
			{
				//status="InProgress";
				throw new BadRequestException("Cannot update task when there are subtasks in progress");

	           // throw new IllegalStateException("Cannot update task when there are subtasks in progress");
			}
			
			
		}
	task1.setDescription(task.getDescription());
	task1.setDue_date(task.getDue_date());
	task1.setStatus(status);
	task1.setName(task.getName());
	task1.setId(task.getId());
	taskRepository.save(task1);
		return task1;
	}
	
	public void deleteTask(  long id)
	{
		taskRepository.deleteById(id);
		
	}
	

}
