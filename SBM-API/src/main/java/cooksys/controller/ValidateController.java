package cooksys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cooksys.service.HashtagService;
import cooksys.service.TweetService;
import cooksys.service.UzerService;

@RestController
//@Validated
@RequestMapping("validate")
public class ValidateController {
	
	private UzerService uzerService;
	private TweetService tweetService;
	private HashtagService tagService;
	
	ValidateController(UzerService uzerService, TweetService tweetService, HashtagService tagService)
	{
		super();
		this.uzerService = uzerService;
		this.tweetService = tweetService;
		this.tagService = tagService;
		
	}
	
	@GetMapping("tag/exists/{label}")
	public Boolean tagExists(@PathVariable String label)
	{
		return tagService.exists(label);
	}
	
	@GetMapping("username/exists/@{username}")
	public Boolean usernameExists(@PathVariable String username)
	{
		return uzerService.exists(username);
	}
	
	@GetMapping("username/available/@{username}")
	public Boolean usernameAvailable(@PathVariable String username)
	{
		return uzerService.available(username);
	}
}
