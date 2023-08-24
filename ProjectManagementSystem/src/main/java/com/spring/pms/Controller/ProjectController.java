package com.spring.pms.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.pms.Entity.Project;
import com.spring.pms.Exceptions.DetailsNotFoundException;
import com.spring.pms.Exceptions.UserAlreadyExistException;
import com.spring.pms.Response.Response201;
import com.spring.pms.Response.Response400;
import com.spring.pms.Response.Response401;
import com.spring.pms.Response.Response403;
import com.spring.pms.Response.Response404;
import com.spring.pms.Response.Response409;
import com.spring.pms.Response.Response500;
import com.spring.pms.Service.ApiRespons;
import com.spring.pms.Service.ProjectService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Project")
public class ProjectController {
	
	@Autowired 
	private ProjectService projectService;
	@ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Project.class), mediaType = "application/json") },description = "Ok"),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = Response500.class),mediaType = "application/json")},description = "Internal Server Error" )
       ,@ApiResponse(responseCode = "401", content = { @Content(schema = @Schema(implementation = Response401.class),mediaType = "application/json")},description = "Unauthorized" ),
       @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema(implementation = Response403.class),mediaType = "application/json")},description = "Forbidden" ),
       @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema(),mediaType = "application/json")},description = "No Content" )


})  
	@PreAuthorize("hasRole('ROLE_MANAGER')")

	@GetMapping("/")
	public ResponseEntity< ApiRespons<List<Project>>> getAllProjects()
	{
		 List<Project> list1=projectService.getAllProjects();
		 if(list1.isEmpty())
		 {
			 throw new DetailsNotFoundException("Projects_wre_not_found");
		 }
	        ApiRespons<List<Project>> response = new ApiRespons<>("Sucess", list1);

		 return new ResponseEntity<>(response,HttpStatus.OK) ;
	}
	@ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Project.class), mediaType = "application/json") },description = "Ok"),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = Response500.class),mediaType = "application/json")},description = "Internal Server Error" ),
       @ApiResponse(responseCode = "401", content = { @Content(schema = @Schema(implementation = Response401.class),mediaType = "application/json")},description = "Unauthorized" ),
       @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema(implementation = Response403.class),mediaType = "application/json")},description = "Forbidden" ),
       @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = Response404.class),mediaType = "application/json")},description = "Notfound" ),
       @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema())},description = "No Content" )
})
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@GetMapping("/{id}")
	public ResponseEntity< ApiRespons<Project> > getAllProject(@PathVariable int id)
	{
		Project project=projectService.getAllProject(id);
		if(project==null)
		{
			throw new DetailsNotFoundException("Projects_wre_not_found_with_id: "+id);
		}
        ApiRespons<Project> response = new ApiRespons<>("Sucess", project);

		 return new ResponseEntity<>(response,HttpStatus.OK) ;
	}
	 @ApiResponses({
	        @ApiResponse(responseCode = "201", content = {
	            @Content(schema = @Schema(implementation = Response201.class), mediaType = "application/json") },description = "Created"),
	        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = Response500.class,example = "{ \"status\": 500, \"message\": \"Database error\" }"),mediaType = "application/json")},description = "Internal Server Error" )
	       , @ApiResponse(responseCode = "400", content = { @Content(examples = {
	               @ExampleObject(name = "InvalidDataType",value = "{\"message\":\"Id_Should_Be_In_Integer_Type\"}"),
	               @ExampleObject(name = "ProjectStatus", value = "{\"message\":\"Should_be_only_Active_or_Inactive\"}"),
	             },schema = @Schema(implementation = Response400.class),mediaType = "application/json")},description = "Bad Request" ),
	       @ApiResponse(responseCode = "409", content = { @Content(schema = @Schema(implementation = Response409.class),mediaType = "application/json")},description = "Conflicts" ),
	       @ApiResponse(responseCode = "401", content = { @Content(schema = @Schema(implementation = Response401.class),mediaType = "application/json")},description = "Unauthorized" ),
	       @ApiResponse(responseCode = "403", content = { @Content( schema = @Schema(implementation = Response403.class),mediaType = "application/json")},description = "Forbidden" )


	})
		@PreAuthorize("hasRole('ROLE_MANAGER')")

	@PostMapping("/")
	public ResponseEntity<ApiRespons<String>>postProject( @Valid @RequestBody Project project)
	{
		Project project1=projectService.getAllProject(project.getId());
		if(project1!=null)
		{
			throw new UserAlreadyExistException("Project_with_this_id: "+project.getId()+"alerady_exist");
		}
		projectService.postProject(project);
        ApiRespons<String> response = new ApiRespons<>("Sucess", "Sucessfully_Created_Project");

		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	@ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation =Project.class ), mediaType = "application/json") },description = "Ok"),
        @ApiResponse(responseCode = "500", content = { @Content(examples = {@ExampleObject(name="DatbaseConnection",value = "{\"message\":\"Check_UserName_And_Password_of_DataBase\"}")}, schema = @Schema(implementation = Response500.class,example = "{ \"status\": 500, \"message\": \"Database error\" }"),mediaType = "application/json")},description = "Internal Server Error" )
       , @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = Response400.class),mediaType = "application/json")},description = "Bad Request" ),

       @ApiResponse(responseCode = "401", content = { @Content(schema = @Schema(implementation = Response401.class),mediaType = "application/json")},description = "Unauthorized" ),
       @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema(implementation = Response403.class),mediaType = "application/json")},description = "Forbidden" )
       


})
	@PreAuthorize("hasRole('ROLE_MANAGER')")

	@PutMapping("/{id}")
	public ResponseEntity<ApiRespons<Project>>  updateProject(@Valid @RequestBody Project project ,  @PathVariable int id)
	{
		Project project1=projectService.getAllProject(id);
		if(project==null)
		{
			throw new DetailsNotFoundException("Projects_wre_not_found_with_id: "+id+"to_update");
		}
        ApiRespons<Project> response = new ApiRespons<>("Sucess", projectService.updateProject(project, id));

		 return new ResponseEntity<>(response,HttpStatus.OK) ;
	}
	
	

}
