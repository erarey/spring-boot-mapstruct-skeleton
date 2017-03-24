package cooksys.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cooksys.dto.ContextDto;
import cooksys.dto.CredentialsDto;
import cooksys.dto.TweetDto;
import cooksys.dto.TweetWrapperDto;
import cooksys.dto.UzerDto;
import cooksys.entity.Hashtag;
import cooksys.entity.Tweet;
import cooksys.entity.Uzer;
import cooksys.entity.embeddable.Credentials;
import cooksys.mapper.HashtagMapper;
import cooksys.mapper.TweetMapper;
import cooksys.mapper.UzerMapper;
import cooksys.repository.HashtagRepository;
import cooksys.repository.TweetRepository;
import cooksys.repository.UzerRepository;

@Service
public class TweetService {

	private Boolean ValidCreds(CredentialsDto creds) {
		// manual mapping
		Credentials credentials = new Credentials();
		credentials.setPassword(creds.getPassword());
		credentials.setUsername(creds.getUsername());
		return ValidCreds(credentials);
	}

	private Boolean ValidCreds(Credentials creds) {
		if (uzerRepository.findByCredentials(creds) != null)
			return true;

		return false;
	}

	private final String USERDOESNOTEXIST = new String(
			"The username doesn't ring any bells. Maybe you spelled it wrong?");
	private final String INVALIDCREDS = new String(
			"There is no active account matching those credentials at this site. Who are you really?");
	private final String ALREADYDELETED = new String("It was already deleted!");
	private final String TWEETDOESNOTEXIST = new String("We were paying attention, but we just couldn't find that tweet!");
	private final String TWEETALREADYDELETED = new String("What are you talking about? That tweet is deleted.");
	
	
	private TweetRepository tweetRepository;
	private UzerRepository uzerRepository;
	private UzerMapper uzerMapper;
	private TweetMapper tweetMapper;
	private HashtagMapper hashtagMapper;
	private HashtagRepository hashtagRepository;
	
	TweetService(TweetMapper tweetMapper, TweetRepository tweetRepository, UzerMapper uzerMapper,
			UzerRepository uzerRepository, HashtagRepository hashtagRepository, HashtagMapper hashtagMapper) {
		super();
		this.uzerMapper = uzerMapper;
		this.uzerRepository = uzerRepository;
		this.tweetMapper = tweetMapper;
		this.tweetRepository = tweetRepository;
		this.hashtagRepository = hashtagRepository;
		this.hashtagMapper = hashtagMapper;
	}
	
	public List<TweetDto> index() {
		List<TweetDto> dtos = tweetRepository.findAll().stream().map(tweetMapper::toTweetDto).collect(Collectors.toList());
		
		return dtos;
	}
	
	public boolean exists(Long id) {
		Tweet tweet = tweetRepository.findById(id);

		if (tweet == null || tweet.getDeleted() == true)
			return false;

		return true;
	}
	
	private Set<Hashtag> parseForHashtags(String s)
	{
		//TODO
		return new HashSet<Hashtag>();
	}
	
	private Set<Uzer> parseForMentions(String s)
	{
		//TODO
		return new HashSet<Uzer>();
	}
	public TweetDto post(TweetWrapperDto wrapper)
	{
		return postWithReplyTo(wrapper, null);
	}
	
	public TweetDto postWithReplyTo(TweetWrapperDto wrapper, Long id) {
		if (ValidCreds(wrapper.getCreds()) == false)
		{
			return new TweetDto(INVALIDCREDS);
		}
		
		Tweet replyTo = null;
		
		if (id != null)
		{
			replyTo = tweetRepository.findById(id);
			if (replyTo == null) return new TweetDto(TWEETDOESNOTEXIST);
		}
		
		TweetDto newDto = new TweetDto();
		
		newDto.setAuthor(uzerRepository.findByCredentialsUsername(wrapper.getCreds().getUsername()));
		
		Set<String> hashtagsStrings = new HashSet<String>();
		
		Set<String> mentionsStrings = new HashSet<String>();
		
		if (wrapper.getContent() == null) //must be a repost
		{
			
		}
		else // has content
		{
			Set<Hashtag> hashtags = parseForHashtags(wrapper.getContent());
		
			Set<Uzer> mentions = parseForMentions(wrapper.getContent());
		
			for (Uzer u : mentions)
			{
				mentionsStrings.add(u.getCredentials().getUsername());
			}
		
			for (Hashtag h : hashtags)
			{
				hashtagsStrings.add(h.getLabel());
			}
		
			newDto.setContent(wrapper.getContent());
		
		}
		
		if (replyTo != null) newDto.setInReplyTo(replyTo);
		
		Tweet result = tweetRepository.save(tweetMapper.toNewTweet(newDto, hashtagsStrings, mentionsStrings));
		Uzer uzerToUpdate = newDto.getAuthor();
		uzerToUpdate.addTweet(result);
		
		//sadly this will not return timestamp, nor the correct id
		
		return newDto;
	}

	public TweetDto getById(Long id) {
		Tweet t = tweetRepository.findById(id);
		
		if (t == null) return new TweetDto(TWEETDOESNOTEXIST);
		if (t.getDeleted() == true) return new TweetDto(TWEETALREADYDELETED);
		
		else return tweetMapper.toTweetDto(tweetRepository.findById(id));
	}

	public TweetDto delete(Long id, Credentials creds) {
		if (ValidCreds(creds) == false) return new TweetDto(INVALIDCREDS);
		
		Tweet t = tweetRepository.findById(id);
		
		if (t.getDeleted() == true)
			return new TweetDto(TWEETALREADYDELETED);
		
		if (t.getAuthor().equals(creds.getUsername()))
		{
			t.setDeleted(true);
		}
		
		return tweetMapper.toTweetDto(t);
	}

	public TweetDto reply(Long id, TweetWrapperDto wrapper) {
		return postWithReplyTo(wrapper, id);
	}

	public String like(Long id, Credentials creds) {
		// TODO Auto-generated method stub
		return null;
	}

	public TweetDto repost(Long id, Credentials creds) {
		TweetWrapperDto newWrapper = new TweetWrapperDto();
		
		newWrapper.setContent(null);
		newWrapper.setCreds(creds);
		
		return postWithReplyTo(newWrapper, id);
	}

	public List<Hashtag> getTags(Long id) {
		Tweet t = tweetRepository.findById(id);
		if (t == null) return new ArrayList<Hashtag>();
		
		Set<String> stringTags = t.getHashtagsInThisTweet();
		
		List<Hashtag> hashtags = hashtagRepository.findAll();
		
		List<Hashtag> hashtagsIdentified = new ArrayList<>();
		
		for (String s : stringTags)
		{
			Hashtag h = hashtagRepository.findByLabeld(s);
			if (h != null) hashtagsIdentified.add(h);
		}
		
		return hashtagsIdentified;
	}

	public List<Uzer> likes(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ContextDto getContext(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
