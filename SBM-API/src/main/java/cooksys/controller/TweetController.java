package cooksys.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cooksys.dto.ContextDto;
import cooksys.dto.TweetDto;
import cooksys.dto.TweetWrapperDto;
import cooksys.entity.Hashtag;
import cooksys.entity.Uzer;
import cooksys.entity.embeddable.Credentials;
import cooksys.service.TweetService;

@RestController
@RequestMapping("tweets")
public class TweetController {

	private TweetService tweetService;
	
	public TweetController(TweetService tweetService)
	{
		super();
		this.tweetService = tweetService;
	}
	
	@GetMapping
	public List<TweetDto> index() {
		return tweetService.index();
	}
	
	@PostMapping
	public TweetDto post(@RequestBody TweetWrapperDto wrapper, HttpServletResponse response)
	{
		if (wrapper == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		return tweetService.post(wrapper);
		
	}
	
	@GetMapping("{id}")
	public TweetDto getById(@PathVariable Long id, HttpServletResponse response)
	{
		if (id == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		
		return tweetService.getById(id);
	}
	
	@DeleteMapping("{id}")
	public TweetDto delete(@PathVariable Long id, @RequestBody Credentials creds, HttpServletResponse response)
	{
		if (id == null || creds == null || creds.getUsername() == null || creds.getPassword() == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		return tweetService.delete(id, creds);
	}
	
	@PostMapping("{id}/reply")
	public TweetDto like(@PathVariable Long id, @RequestBody TweetWrapperDto wrapper, HttpServletResponse response)
	{
		if (id == null || wrapper == null || wrapper.getContent() == null || wrapper.getCreds() == null || wrapper.getCreds().getUsername() == null || wrapper.getCreds().getPassword() == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		return tweetService.reply(id, wrapper);
	}
	
	@PostMapping("{id}/like")
	public String reply(@PathVariable Long id, @RequestBody Credentials creds, HttpServletResponse response)
	{
		if (id == null || creds == null || creds.getUsername() == null || creds.getPassword() == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		return tweetService.like(id, creds);
	}
	
	@PostMapping("{id}/repost")
	public TweetDto repost(@PathVariable Long id, @RequestBody Credentials creds, HttpServletResponse response)
	{
		if (id == null || creds == null || creds.getUsername() == null || creds.getPassword() == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		return tweetService.repost(id, creds);
	}
	@GetMapping("{id}/tags")
	public List<Hashtag> getTags(@PathVariable Long id, HttpServletResponse response)
	{
		if (id == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		return tweetService.getTags(id);
	}
	
	@GetMapping("{id}/likes")
	public List<Uzer> likes(@PathVariable Long id, HttpServletResponse response)
	{
		if (id == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		return tweetService.likes(id);
	}
	
	@GetMapping("{id}/context")
	public ContextDto context(@PathVariable Long id, HttpServletResponse response)
	{
		if (id == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		return tweetService.getContext(id);
		
	}
}
