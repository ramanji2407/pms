package com.spring.pms.Entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="User_name")
	@NotEmpty(message = "Task_name_shoulde_not_be_empty")
	private String name;
	
	private String password;
	@Email(message = "please_enter_valid_email_adress ")
	@NotEmpty(message = "Email Shoulde not be empty")
	private String email;
	@Pattern(regexp = "^(ROLE_MANAGER|ROLE_USER)$")
	private String role;
	@Pattern(regexp = "^(Backend|Frontend)$")
	private String department; 
	
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER )
	Set<Project>projects;
    @JsonIgnore

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	@JoinColumn(name = "User_id")
	private List<Task>tasks;
	@JsonIgnore

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "assigned_user_id") 
 	private List<Subtask>subtasks;

	


}