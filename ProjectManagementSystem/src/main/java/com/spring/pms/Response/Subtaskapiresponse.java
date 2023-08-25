package com.spring.pms.Response;

import com.spring.pms.Entity.Project;
import com.spring.pms.Entity.Subtask;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subtaskapiresponse<T> {
	@Schema(example = "Sucess")
	private String message;
	@Schema(implementation = Subtask.class)
    private T data;
}
