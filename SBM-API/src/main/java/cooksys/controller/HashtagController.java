package cooksys.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cooksys.dto.HashtagDto;
import cooksys.dto.TweetDto;
import cooksys.entity.Hashtag;
import cooksys.entity.Tweet;
import cooksys.service.HashtagService;
import cooksys.service.TweetService;

@RestController
@RequestMapping("tags")
public class HashtagController {

	private HashtagService hashtagService;
	
	public HashtagController(HashtagService hashtagService)
	{
		super();
		this.hashtagService = hashtagService;
	}
	
	@GetMapping
	public List<HashtagDto> index() {
		return hashtagService.index();
	}
	
	@GetMapping("{label}")
	public List<TweetDto> getTweetsByLabel(@PathVariable String label, HttpServletResponse response)
	{
		if (label == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		return hashtagService.getTweetsByLabel(label);
	}
}
