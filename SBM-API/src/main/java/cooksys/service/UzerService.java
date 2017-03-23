package cooksys.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import cooksys.dto.CredentialsDto;
import cooksys.dto.TweetDto;
import cooksys.dto.UzerDto;
import cooksys.dto.UzerPatchWrapperDto;
import cooksys.entity.Tweet;
import cooksys.entity.Uzer;
import cooksys.entity.embeddable.Credentials;
import cooksys.entity.embeddable.Profile;
import cooksys.mapper.TweetMapper;
import cooksys.mapper.UzerMapper;
import cooksys.repository.UzerRepository;
import org.springframework.stereotype.Service;


@Service
public class UzerService {

	private Boolean ValidCreds(CredentialsDto creds)
	{
		//manual mapping
		Credentials credentials = new Credentials();
		credentials.setPassword(creds.getPassword());
		credentials.setUsername(creds.getUsername());
		return ValidCreds(credentials);
	}
	
	private Boolean ValidCreds(Credentials creds)
	{
		if (uzerRepository.findByCredentials(creds) != null) return true;
		
		return false;
	}
	
	private final String USERDOESNOTEXIST = new String("The username doesn't ring any bells. Maybe you spelled it wrong?");
	private final String INVALIDCREDS = new String("There is no active account matching those credentials at this site. Who are you really?");
	private final String ALREADYDELETED = new String("You were already deleted!");
	private final String ALREADYFOLLOWING = new String("You were already following them!");
	private final String ALREADYNOTFOLLOWING = new String("You weren't even following them!");
	
	private UzerRepository uzerRepository;
	private UzerMapper uzerMapper;
	private TweetMapper tweetMapper;
	
	UzerService(UzerMapper uzerMapper, UzerRepository uzerRepository, TweetMapper tweetMapper)
	{
		super();
		this.uzerMapper = uzerMapper;
		this.uzerRepository = uzerRepository;
		this.tweetMapper = tweetMapper;
	}
	
	public List<UzerDto> index() {
		List<UzerDto> dtos = uzerRepository.findAll().stream()
				.map(uzerMapper::toUzerDto)
				.collect(Collectors.toList());
		
		//System.out.println(dtos.get(0).getUsername());
		
		return dtos;
	}

	public boolean exists(String username) {
		Uzer uzer = uzerRepository.findByCredentialsUsername(username);
		
		if (uzer == null || uzer.getDeleted() == true) return false;
		
		return true;
	}

	public boolean available(String username) {
		Uzer uzer = uzerRepository.findByCredentialsUsername(username);
		
		if (uzer == null) return true;
		
		return false;
	}

	public UzerDto getByUsername(String username) {
		return uzerMapper.toUzerDto(uzerRepository.findByCredentialsUsername(username));
	}

	public UzerDto post(UzerPatchWrapperDto dto) {
		//check if username is available
		
		Uzer newUzer = uzerMapper.toNewUzer(dto.getProfile(), dto.getCreds());
		
		Uzer result = uzerRepository.save(newUzer);
		
		result = uzerRepository.findByCredentialsUsername(result.getCredentials().getUsername());
		
		System.out.println("new user's timestamp was: " + result.getJoined());
		//if it was successful, return the Dto of the user just created.
		
		UzerDto dto2 = uzerMapper.toUzerDto(result);
		
		System.out.println("new user dto's username ultimately was: " + dto2.getUsername());
		
		return dto2;
	}

	public UzerDto patch(String username, UzerPatchWrapperDto patch) {
		if (username != patch.getCreds().getUsername() || ValidCreds(patch.getCreds()) == false)
		{
			return new UzerDto(INVALIDCREDS);
		}
		
		//if creds are invalid return null;
		
		Uzer uzer = uzerRepository.findByCredentialsUsername(username);
		Profile profile = new Profile(patch.getProfile());
		Profile patchedP = uzer.getProfile();
		if (profile.getEmail() != null) patchedP.setEmail(profile.getEmail());
		if (profile.getFirstName() != null) patchedP.setFirstName(profile.getFirstName());
		if (profile.getLastName() != null) patchedP.setLastName(profile.getLastName());
		if (profile.getPhone() != null) patchedP.setPhone(profile.getPhone());
		
		uzer.setProfile(patchedP);
		
		uzerRepository.save(uzer);
		
		return uzerMapper.toUzerDto(uzerRepository.findByCredentialsUsername(username));
	}

	public UzerDto delete(String username, Credentials credentialsDto) {
		if (ValidCreds(credentialsDto) == false) return new UzerDto(INVALIDCREDS);
		Uzer uzer = uzerRepository.findByCredentialsUsername(username);
		if (uzer == null) return new UzerDto(USERDOESNOTEXIST);
		if (uzer.getDeleted() == true) return new UzerDto(ALREADYDELETED); //return some error, already deleted
		
		uzer.setDeleted(true);
		
		uzerRepository.save(uzer);
		
		return uzerMapper.toUzerDto(uzer);
	}

	public String follow(String username, CredentialsDto cred) {
		if (ValidCreds(cred) == false) 
			return new String(INVALIDCREDS);
		if (exists(username) == false) 
			return new String(USERDOESNOTEXIST);
		
		Uzer uzerToFollow = uzerRepository.findByCredentialsUsername(username);
		Uzer requestor = uzerRepository.findByCredentialsUsername(cred.getUsername());
		if (!requestor.follow(uzerToFollow)) return new String(ALREADYFOLLOWING);
		
		return new String("OK");
		
		//uzerToFollow.addFollowing(requestor);
	}

	public String unfollow(String username, CredentialsDto cred) {
		if (ValidCreds(cred) == false) 
			return new String(INVALIDCREDS);
		if (exists(username) == false) 
			return new String(USERDOESNOTEXIST);
		
		Uzer uzerToUnfollow = uzerRepository.findByCredentialsUsername(username);
		Uzer requestor = uzerRepository.findByCredentialsUsername(cred.getUsername());
		if (!requestor.unfollow(uzerToUnfollow)) return new String(ALREADYNOTFOLLOWING);
		
		return new String("OK");
	}

	public List<TweetDto> getFeed(String username) {
		if (exists(username) == false)
		{
			List<TweetDto> feedDto = new ArrayList<TweetDto>();
			feedDto.add(new TweetDto(USERDOESNOTEXIST));
			return feedDto;
		}
		
		ArrayList<Tweet> feed = new ArrayList<Tweet>();
		
		Uzer uzer = uzerRepository.findByCredentialsUsername(username);
		
		feed.addAll(uzer.getTweets().stream()
				.filter(x -> x.getDeleted() ? false : true)
				.collect(Collectors.toList()));
		
		for (Uzer u : uzer.getFollowing())
		{
			feed.addAll(u.getTweets().stream()
					.filter(x -> x.getDeleted() ? false : true)
					.collect(Collectors.toList()));
		}
		
		return feed.stream()
				.sorted(Collections.reverseOrder((e1, e2) -> Long.compare(e1.getPosted().getTime(), e2.getPosted().getTime())))
				.map(tweetMapper::toTweetDto)
				.collect(Collectors.toList());
	}

	public List<TweetDto> getTweets(String username) {
		if (exists(username) == false)
		{
			List<TweetDto> feedDto = new ArrayList<TweetDto>();
			feedDto.add(new TweetDto(USERDOESNOTEXIST));
			return feedDto;
		}
		
		Uzer uzer = uzerRepository.findByCredentialsUsername(username);
		
		List<Tweet> list = new ArrayList<Tweet>();
		list.addAll(uzer.getTweets());
		return list.stream()
				.filter(x -> x.getDeleted() ? false : true)
				.sorted(Collections.reverseOrder((e1, e2) -> Long.compare(e1.getPosted().getTime(), e2.getPosted().getTime())))
				.map(tweetMapper::toTweetDto)
				.collect(Collectors.toList());
	}

	public List<UzerDto> getFollowers(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UzerDto> getFollowing(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TweetDto> getMentions(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
