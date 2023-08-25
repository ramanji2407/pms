package com.spring.pms.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.pms.Dto.Request;
import com.spring.pms.Entity.Project;
import com.spring.pms.Entity.User;
import com.spring.pms.Exceptions.DetailsNotFoundException;
import com.spring.pms.Response.Response201;
import com.spring.pms.Response.Response400;
import com.spring.pms.Response.Response401;
import com.spring.pms.Response.Response403;
import com.spring.pms.Response.Response404;
import com.spring.pms.Response.Response409;
import com.spring.pms.Response.Response500;
import com.spring.pms.Response.ResponseMessage;
import com.spring.pms.Response.Userapiresponse;
import com.spring.pms.Service.ApiRespons;
import com.spring.pms.Service.Jwtservice;
import com.spring.pms.Service.ProjectService;
import com.spring.pms.Service.UserService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/User")
public class UserController {
	@Autowired
private	UserService userService;
	@Autowired
	private Jwtservice jwtservice;
	@Autowired
	private AuthenticationManager authmanager;
	@Autowired 
	private ProjectService projectService;
	@ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Userapiresponse.class), mediaType = "application/json") },description = "Ok"),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = Response500.class),mediaType = "application/json")},description = "Internal Server Error" )
       ,@ApiResponse(responseCode = "401", content = { @Content(schema = @Schema(implementation = Response401.class),mediaType = "application/json")},description = "Unauthorized" ),
       @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema(implementation = Response403.class),mediaType = "application/json")},description = "Forbidden" ),
       @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema(),mediaType = "application/json")},description = "No Content" )


})  	
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@GetMapping("/")
	public ResponseEntity< ApiRespons<List<User>>> getAllUsers()
	{
		List<User> list1=userService.getAllUsers();
		if(list1.isEmpty())
		{
			 throw new DetailsNotFoundException("No_Users_wre_not_found");

		}
        ApiRespons<List<User>> response = new ApiRespons<>("Sucess", list1);

		 return new ResponseEntity<>(response,HttpStatus.OK) ;
			
	}
	@ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Userapiresponse.class), mediaType = "application/json") },description = "Ok"),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = Response500.class),mediaType = "application/json")},description = "Internal Server Error" ),
       @ApiResponse(responseCode = "401", content = { @Content(schema = @Schema(implementation = Response401.class),mediaType = "application/json")},description = "Unauthorized" ),
       @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema(implementation = Response403.class),mediaType = "application/json")},description = "Forbidden" ),
       @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = Response404.class),mediaType = "application/json")},description = "Notfound" ),
       @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema())},description = "No Content" )
})
	@PreAuthorize("hasRole('ROLE_MANAGER')")

	@GetMapping("/{id}")
	public ResponseEntity< ApiRespons<User>> getAllUser(@PathVariable int id)
	{User user=userService.getAllUser(id);
	if(user==null)
	{
		throw new DetailsNotFoundException("User_wre_not_found_with_id: "+id);

	}
    ApiRespons<User> response = new ApiRespons<>("Sucess", user);

	 return new ResponseEntity<>(response,HttpStatus.OK) ;
	}
	
	@ApiResponses({
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = Response201.class), mediaType = "application/json") },description = "Created"),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = Response500.class,example = "{ \"status\": 500, \"message\": \"Database error\" }"),mediaType = "application/json")},description = "Internal Server Error" )
       , @ApiResponse(responseCode = "400", content = { @Content(examples = {
               @ExampleObject(name = "Role",value = "{\"message\":\"Role_should_be_either_USER_or_Admin\"}"),
               @ExampleObject(name = "Department", value = "{\"message\":\"Department_should_be_either_Backend_or_Frontend\"}"),
            },schema = @Schema(implementation = Response400.class),mediaType = "application/json")},description = "Bad Request" ),
       @ApiResponse(responseCode = "409", content = { @Content(schema = @Schema(implementation = Response409.class),mediaType = "application/json")},description = "Conflicts" ),
       @ApiResponse(responseCode = "401", content = { @Content(schema = @Schema(implementation = Response401.class),mediaType = "application/json")},description = "Unauthorized" ),
       @ApiResponse(responseCode = "403", content = { @Content( schema = @Schema(implementation = Response403.class),mediaType = "application/json")},description = "Forbidden" )


})

	@PostMapping("/post/{id}")
	public ResponseEntity<ApiRespons<String>> postUser(@Valid @RequestBody User user, @PathVariable int id)
	{
		
		
		userService.postUser(user, id);
        ApiRespons<String> response = new ApiRespons<>("Sucess", "Sucessfully_Created_User");

		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation =Userapiresponse.class ), mediaType = "application/json") },description = "Ok"),
        @ApiResponse(responseCode = "500", content = { @Content(examples = {@ExampleObject(name="DatbaseConnection",value = "{\"message\":\"Check_UserName_And_Password_of_DataBase\"}")}, schema = @Schema(implementation = Response500.class,example = "{ \"status\": 500, \"message\": \"Database error\" }"),mediaType = "application/json")},description = "Internal Server Error" )
       , @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = Response400.class),mediaType = "application/json")},description = "Bad Request" ),

       @ApiResponse(responseCode = "401", content = { @Content(schema = @Schema(implementation = Response401.class),mediaType = "application/json")},description = "Unauthorized" ),
       @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema(implementation = Response403.class),mediaType = "application/json")},description = "Forbidden" )
       


})
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@PutMapping("/{id}")
	public ResponseEntity<ApiRespons<User>>  updateUser(@Valid @RequestBody User user ,  @PathVariable int id)
	{
		User user1=userService.getAllUser(id);
		if(user1==null)
		{
			throw new DetailsNotFoundException("Users_wre_not_found_with_id: "+id+"to_update");

		}
	    ApiRespons<User> response = new ApiRespons<>("Sucess", userService.updateUser(user, id));


		return new ResponseEntity<>( response,HttpStatus.OK);
	}
	 	
	@ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ResponseMessage.class), mediaType = "application/json") },description = "Created"),
       
       @ApiResponse(responseCode = "403", content = { @Content( schema = @Schema(implementation = ResponseMessage.class),mediaType = "application/json")},description = "Forbidden" )


})

	@PostMapping("/token")
	@ResponseBody
	public ResponseEntity<ResponseMessage> generateToken(@RequestBody Request request) {
	    try {
	        Authentication authentication = authmanager.authenticate(
	            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
	        );

	        if (authentication.isAuthenticated()) {
	Jwtservice jwtService = new Jwtservice();

	            String token = jwtService.generateToken(request.getUsername());
	            ResponseMessage responseMessage = new ResponseMessage("success", "Token generated successfully", token);
	            return ResponseEntity.ok(responseMessage);
	        } else {
	            ResponseMessage responseMessage = new ResponseMessage("error", "Authentication failed", null);
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMessage);
	        }
	    } catch (Exception e) {
	        ResponseMessage responseMessage = new ResponseMessage("error", "Authentication failed", null);
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMessage);
	    }
	}

	

	
}
