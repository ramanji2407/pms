package com.spring.pms.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.pms.Entity.Subtask;
import com.spring.pms.Exceptions.DetailsNotFoundException;
import com.spring.pms.Exceptions.UserAlreadyExistException;
import com.spring.pms.Response.Response200;
import com.spring.pms.Response.Response201;
import com.spring.pms.Response.Response400;
import com.spring.pms.Response.Response401;
import com.spring.pms.Response.Response403;
import com.spring.pms.Response.Response404;
import com.spring.pms.Response.Response409;
import com.spring.pms.Response.Response500;
import com.spring.pms.Service.ApiRespons;
import com.spring.pms.Service.SubTaskService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Subtask")
public class SubtaskController {
	@Autowired
	private SubTaskService subTaskService;
	@ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Subtask.class), mediaType = "application/json") },description = "Ok"),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = Response500.class),mediaType = "application/json")},description = "Internal Server Error" )
       ,@ApiResponse(responseCode = "401", content = { @Content(schema = @Schema(implementation = Response401.class),mediaType = "application/json")},description = "Unauthorized" ),
       @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema(implementation = Response403.class),mediaType = "application/json")},description = "Forbidden" ),
       @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema(),mediaType = "application/json")},description = "No Content" )


})  	

	@PreAuthorize("hasRole('ROLE_MANAGER')")

	@GetMapping("/")
	public ResponseEntity<ApiRespons<List<Subtask>>> getAllSubtasks()
	{
		if(subTaskService.getAllSubtasks().isEmpty())
		{
			 throw new DetailsNotFoundException("Subtasks_wre_not_found");

		}
		ApiRespons<List<Subtask>> response = new ApiRespons<>("Sucess", subTaskService.getAllSubtasks());

		 return new ResponseEntity<>(response,HttpStatus.OK) ;
	}
	@ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Subtask.class), mediaType = "application/json") },description = "Ok"),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = Response500.class),mediaType = "application/json")},description = "Internal Server Error" ),
       @ApiResponse(responseCode = "401", content = { @Content(schema = @Schema(implementation = Response401.class),mediaType = "application/json")},description = "Unauthorized" ),
       @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema(implementation = Response403.class),mediaType = "application/json")},description = "Forbidden" ),
       @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = Response404.class),mediaType = "application/json")},description = "Notfound" ),
       @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema())},description = "No Content" )

})	

	@PreAuthorize("hasRole('ROLE_MANAGER')")

	@GetMapping("/{id}")
	public ResponseEntity<ApiRespons<Subtask>> getAllSubtask(@PathVariable long id)
	{
		if( subTaskService.getAllSubtask(id)==null)
		{
			 throw new DetailsNotFoundException("SubTasks_wre_not_found_with_that_id");

		}
		ApiRespons<Subtask> response = new ApiRespons<>("Sucess",subTaskService.getAllSubtask(id));

		 return new ResponseEntity<>(response,HttpStatus.OK) ;
	}
	 @ApiResponses({
	        @ApiResponse(responseCode = "201", content = {
	            @Content(schema = @Schema(implementation = Response201.class), mediaType = "application/json") },description = "Created"),
	        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = Response500.class,example = "{ \"status\": 500, \"message\": \"Database error\" }"),mediaType = "application/json")},description = "Internal Server Error" )
	       , @ApiResponse(responseCode = "400", content = { @Content(examples = {
	               
	               @ExampleObject(name = "Subtaskstatus", value = "{\"message\":\"Status_should_be_either_Completed_Progress\"}")},schema = @Schema(implementation = Response400.class),mediaType = "application/json")},description = "Bad Request" ),
	       @ApiResponse(responseCode = "409", content = { @Content(schema = @Schema(implementation = Response409.class),mediaType = "application/json")},description = "Conflicts" ),
	       @ApiResponse(responseCode = "401", content = { @Content(schema = @Schema(implementation = Response401.class),mediaType = "application/json")},description = "Unauthorized" ),
	       @ApiResponse(responseCode = "403", content = { @Content( schema = @Schema(implementation = Response403.class),mediaType = "application/json")},description = "Forbidden" )


	})
	
		@PreAuthorize("hasRole('ROLE_MANAGER')")

	@PostMapping("/{id}")
	public  ResponseEntity<ApiRespons<String>> postSubtask(@Valid @RequestBody Subtask subtask, @PathVariable long id)
	{
	
		if(subTaskService.getAllSubtask(subtask.getId())!=null)
		{
			throw new UserAlreadyExistException("Task_with_this_id: "+subtask.getId()+"alerady_exist");
		}
		subTaskService.postSubtask(subtask, id);

        ApiRespons<String> response = new ApiRespons<>("Sucess", "Sucessfully_Created_SubTask");
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	@ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation =Subtask.class ), mediaType = "application/json") },description = "Ok"),
        @ApiResponse(responseCode = "500", content = { @Content(examples = {@ExampleObject(name="DatbaseConnection",value = "{\"message\":\"Check_UserName_And_Password_of_DataBase\"}")}, schema = @Schema(implementation = Response500.class,example = "{ \"status\": 500, \"message\": \"Database error\" }"),mediaType = "application/json")},description = "Internal Server Error" )
       , @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = Response400.class),mediaType = "application/json")},description = "Bad Request" ),

       @ApiResponse(responseCode = "401", content = { @Content(schema = @Schema(implementation = Response401.class),mediaType = "application/json")},description = "Unauthorized" ),
       @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema(implementation = Response403.class),mediaType = "application/json")},description = "Forbidden" )
       


})

	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MANAGER')")

	@PutMapping("/{id}")
	public ResponseEntity< ApiRespons<Subtask>> updateSubtask(@Valid @RequestBody Subtask subtask ,  @PathVariable long id)
	{
		if(subTaskService.getAllSubtask(id)==null)
		{
			 throw new DetailsNotFoundException("SubTasks_wre_not_found_with_that_id_to_update");
	
		}
        ApiRespons<Subtask> response = new ApiRespons<>("Sucess",subTaskService.updateSubtask(subtask, id));

		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	 
	@ApiResponses({
	        @ApiResponse(responseCode = "200", content = {
	            @Content(schema = @Schema(implementation = Response200.class), mediaType = "application/json") },description = "Ok"),
	        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = Response500.class,example = "{ \"status\": 500, \"message\": \"Database error\" }"),mediaType = "application/json")},description = "Internal Server Error" ),
	       @ApiResponse(responseCode = "401", content = { @Content(schema = @Schema(implementation = Response401.class),mediaType = "application/json")},description = "Unauthorized" ),
	       @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema(implementation = Response403.class),mediaType = "application/json")},description = "Forbidden" ),
	       @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = Response404.class),mediaType = "application/json")},description = "Notfound" )

	})

	@PreAuthorize("hasRole('ROLE_MANAGER')")

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiRespons<String>> deleteSubtask( @PathVariable long id)
	{
		if(subTaskService.getAllSubtask(id)==null)
		{
			 throw new DetailsNotFoundException("SubTasks_wre_not_found_with_that_id_to_Delete");
	
		}
		 ApiRespons<String> response = new ApiRespons<>("Sucess","Deleted_Sucessfully");

			return new ResponseEntity<>(response,HttpStatus.OK);
		
		}
	

}
