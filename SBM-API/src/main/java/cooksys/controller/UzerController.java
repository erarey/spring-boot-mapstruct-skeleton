package cooksys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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

import cooksys.dto.CredentialsDto;
import cooksys.dto.TweetDto;
import cooksys.dto.UzerDto;
import cooksys.dto.UzerPatchWrapperDto;
import cooksys.entity.Uzer;
import cooksys.entity.embeddable.Credentials;
import cooksys.entity.embeddable.Profile;
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
		System.out.println("trying to return all users");
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
	
	private boolean has(String username) {
		// TODO Auto-generated method stub
		return true;
	}

	@PostMapping //users
	public UzerDto post(@RequestBody UzerPatchWrapperDto dto, HttpServletResponse response)
	{
		if (dto == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		return uzerService.post(dto);
	}
	
	@PatchMapping("@{username}")
	public UzerDto patch(@PathVariable String username, @RequestBody @Validated UzerPatchWrapperDto patch, HttpServletResponse response)
	{
		//if (!has(username))
		//{
			
		//}
		
		return uzerService.patch(username, patch);
	}
	
	@DeleteMapping("@{username}")
	public UzerDto delete(@PathVariable String username)
	{
		if (!has(username))
		{
			
		}
		
		return uzerService.delete(username);
	}
	
	@PostMapping("@{username}/follow")
	public String follow(@PathVariable String username, @RequestBody @Validated CredentialsDto cred, HttpServletResponse response)
	{
		return uzerService.follow(username, cred);
		
	}
	
	@PostMapping("@{username}/unfollow")
	public String unfollow(@PathVariable String username, @RequestBody @Validated CredentialsDto cred, HttpServletResponse response)
	{
		return uzerService.unfollow(username, cred);
		
	}
	
	@GetMapping("@{username}/feed")
	public List<TweetDto> getFeed(@PathVariable String username, HttpServletResponse response)
	{
		if (username == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return new ArrayList<TweetDto>();
		}
		
		return uzerService.getFeed(username);
	}
	
	@GetMapping("@{username}/tweets")
	public List<TweetDto> getTweets(@PathVariable String username, HttpServletResponse response)
	{
		if (username == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return new ArrayList<TweetDto>();
		}
		
		return uzerService.getTweets(username);
	}
	
	@GetMapping("@{username}/followers")
	public List<UzerDto> getFollowers(@PathVariable String username, HttpServletResponse response)
	{
		if (username == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return new ArrayList<UzerDto>();
		}
		
		return uzerService.getFollowers(username);
	}
	
	@GetMapping("@{username}/following")
	public List<UzerDto> getFollowing(@PathVariable String username, HttpServletResponse response)
	{
		if (username == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return new ArrayList<UzerDto>();
		}
		
		return uzerService.getFollowing(username);
	}
	
	@GetMapping("@{username}/mentions")
	public List<TweetDto> getMentions(@PathVariable String username, HttpServletResponse response)
	{
		if (username == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return new ArrayList<TweetDto>();
		}
		
		return uzerService.getMentions(username);
	}
	
	
	
	
}
