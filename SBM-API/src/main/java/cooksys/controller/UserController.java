package cooksys.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cooksys.dto.UserDto;
import cooksys.service.UserService;

@RestController
//@Validated
@RequestMapping("users")
public class UserController {

	private UserService userService;
	
	public UserController(UserService userService)
	{
		super();
		this.userService = userService;
		
	}
	
	@GetMapping
	public List<UserDto> index() {
		return userService.index();
	}
	
	@GetMapping("validate/username/exists/@{username}")
	public boolean exists(@PathVariable String username)
	{
		return userService.exists(username);
	}
	
	@GetMapping("validate/username/available/@{username}")
	public boolean available(@PathVariable String username)
	{
		return userService.available(username);
	}	
	
	@GetMapping("@{username}")
	public UserDto getByUsername(@PathVariable String username, HttpServletResponse response)
	{
		if (username != null && has(username))
			return userService.getUserByUsername(username);
		else
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		
		return null;
	}
	
	@PostMapping //users
	public UserDto post(@RequestBody UserDto dto, HttpServletResponse response)
	{
		if (dto == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		UserDto newUserDto = userService.post(dto);
		
		switch(userService.accountStatus(newUser))
		{
		case CREATED:
			return newUserDto;
			break;
		case REACTIVATED:
			//was deleted, creds are ok, now reactivated
			return newUserDto;
			break;
		case DELETEDBADCRED:
			//was deleted, creds were bad, so sad.
			//response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
			break;
		case INVALIDINPUT
			//something required was null.
			//response.setStatus(HttpServletResponse.);
			break;
		}
		
			
	}
}
