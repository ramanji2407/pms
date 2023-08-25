package com.spring.pms.Response;

import com.spring.pms.Entity.Task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taskapiresponse<T> {
	@Schema(example = "Sucess")
	private String message;
	@Schema(implementation = Task.class)
    private T data;

}
