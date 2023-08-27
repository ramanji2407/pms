package com.spring.pms.Entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema( example = "1")
	private int id;
	
	@Schema( example = "ram")
	@Column(name="User_name",unique = true)
	@NotEmpty(message = "Name_shoulde_not_be_empty")
	private String name;
	@Schema( example = "ram")
	@NotEmpty(message = "Pasword_shoulde_not_be_empty")
	private String password;
	@Schema(example = "pasupuletiramanji3@gmail.com")
	@Email(message = "please_enter_valid_email_adress ")
	@NotEmpty(message = "Email Shoulde not be empty")
	private String email;
	@Schema(example = "ROLE_MANAGER")
	@Pattern(regexp = "^(ROLE_MANAGER|ROLE_USER)$",message = "Role_shoulde_be_either_user_admin")
	private String role;
	
	@Schema(example = "Backend")
	@Pattern(regexp = "^(Backend|Frontend)$" ,message = "Department_should_be_either_backend_frontend")
	private String department; 
	
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER )
	Set<Project>projects;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	@JoinColumn(name = "User_id")
	private List<Task>tasks;
    @JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "assigned_user_id") 
 	private List<Subtask>subtasks;

	


}