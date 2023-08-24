package com.spring.pms.Entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		@NotEmpty(message = "Task_name_shoulde_not_be_empty")
	    private String name;
		@NotEmpty(message = "Description_shoulde_not_be_empty")
	    private String description;
	    
		@JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "MM/dd/yyyy")
	    private Date due_date;
		@Pattern(regexp = "^(Completed|InProgress)$")
		private String status;
		
		@JsonIgnore
		@ManyToOne
		private Project project;
		@ JsonIgnore
		@ManyToOne
		private User user;
		@ JsonIgnore
		@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
		@JoinColumn(name ="Task_id")
		List<Subtask>subtask;

}