package cooksys.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cooksys.dto.UserDto;
import cooksys.service.userService;

@RestController
//@Validated
@RequestMapping("users")
public class UzerController {

	private userService userService;
	
	public UzerController(userService userService)
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
		
		return userService.post(dto);
	}
	
	@PatchMapping("@{username}")
	public void patch(@PathVariable Long id, @RequestBody @Validated UserDto dto, HttpServletResponse response)
	{
		if (!has(id))
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		
		userService.patch(id);
	}
	
	@PostMapping("@{username}")
	public void delete(@PathVariable)
}
