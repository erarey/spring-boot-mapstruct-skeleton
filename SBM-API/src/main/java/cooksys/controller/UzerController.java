package cooksys.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cooksys.dto.UzerDto;
import cooksys.dto.UzerPatchWrapperDto;
import cooksys.entity.Uzer;
import cooksys.service.UzerService;

@RestController
//@Validated
@RequestMapping("users")
public class UzerController {

	private UzerService uzerService;
	
	public UzerController(UzerService uzerService)
	{
		super();
		this.uzerService = uzerService;
		
	}
	
	@GetMapping
	public List<UzerDto> index() {
		return uzerService.index();
	}
	
	@GetMapping("validate/username/exists/@{username}")
	public boolean exists(@PathVariable String username)
	{
		return uzerService.exists(username);
	}
	
	@GetMapping("validate/username/available/@{username}")
	public boolean available(@PathVariable String username)
	{
		return uzerService.available(username);
	}	
	
	@GetMapping("@{username}")
	public UzerDto getByUsername(@PathVariable String username, HttpServletResponse response)
	{
		if (username != null && has(username))
			return uzerService.getByUsername(username);
		else
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		
		return null;
	}
	
	@PostMapping //users
	public UserDto post(@RequestBody UzerDto dto, HttpServletResponse response)
	{
		if (dto == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		return uzerService.post(dto);
	}
	
	@PatchMapping("@{username}")
	public UzerDto patch(@PathVariable String username, @RequestBody @Validated UserPatchWrapperDto patch, HttpServletResponse response)
	{
		if (!has(username))
		{
			
		}
		
		userService.patch(username, patch);
	}
	
	@DeleteMapping("@{username}")
	public UzerDto delete(@PathVariable String username)
	{
		if (!has(username))
		{
			
		}
		
		String responseString = uzerService.delete(username);
	}
	
	@PostMapping("@{username}/follow")
	public ResponseEntity follow(@PathVariable String username, @RequestBody @Validated CredentialsDto cred, HttpServletResponse response)
	{
		//why does Eclipse think follow and unfollow are known methods 
		//before they were even created?
		String responseString = uzerService.follow(username, cred);
		
	}
	
	@PostMapping("@{username}/unfollow")
	public ResponseEntity follow(@PathVariable String username, @RequestBody @Validated CredentialsDto cred, HttpServletResponse response)
	{
		String responseString = uzerService.unfollow(username, cred);
		
	}
	
	
}
