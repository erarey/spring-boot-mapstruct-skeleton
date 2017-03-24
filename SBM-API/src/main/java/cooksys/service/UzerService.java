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
import cooksys.mapper.HashtagMapper;
import cooksys.mapper.TweetMapper;
import cooksys.mapper.UzerMapper;
import cooksys.repository.HashtagRepository;
import cooksys.repository.TweetRepository;
import cooksys.repository.UzerRepository;
import org.springframework.stereotype.Service;

@Service
public class UzerService {

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
	private final String ALREADYDELETED = new String("You were already deleted!");
	private final String ALREADYFOLLOWING = new String("You were already following them!");
	private final String ALREADYNOTFOLLOWING = new String("You weren't even following them!");
	private final String USERNAMENOTAVAILABLE = new String("Unfortunately someone else is called that, please think of a different username.");
	
	private TweetRepository tweetRepository;
	private UzerRepository uzerRepository;
	private UzerMapper uzerMapper;
	private TweetMapper tweetMapper;
	private HashtagMapper hashtagMapper;
	private HashtagRepository hashtagRepository;
	
	UzerService(TweetMapper tweetMapper, TweetRepository tweetRepository, UzerMapper uzerMapper,
			UzerRepository uzerRepository, HashtagRepository hashtagRepository, HashtagMapper hashtagMapper) {
		super();
		this.uzerMapper = uzerMapper;
		this.uzerRepository = uzerRepository;
		this.tweetMapper = tweetMapper;
		this.tweetRepository = tweetRepository;
		this.hashtagRepository = hashtagRepository;
		this.hashtagMapper = hashtagMapper;
	}
	public List<UzerDto> index() {
		List<UzerDto> dtos = uzerRepository.findAll().stream().map(uzerMapper::toUzerDto).collect(Collectors.toList());

		// System.out.println(dtos.get(0).getUsername());

		return dtos;
	}

	public boolean exists(String username) {
		Uzer uzer = uzerRepository.findByCredentialsUsername(username);

		if (uzer == null || uzer.getDeleted() == true)
			return false;

		return true;
	}

	public boolean available(String username) {
		Uzer uzer = uzerRepository.findByCredentialsUsername(username);

		if (uzer == null)
			return true;

		return false;
	}

	public UzerDto getByUsername(String username) {
		return uzerMapper.toUzerDto(uzerRepository.findByCredentialsUsername(username));
	}

	public UzerDto post(UzerPatchWrapperDto dto) {
		if (available(dto.getCreds().getUsername()) == false)
		{
			return new UzerDto(USERNAMENOTAVAILABLE);
		}

		Uzer newUzer = uzerMapper.toNewUzer(dto.getProfile(), dto.getCreds());

		Uzer result = uzerRepository.save(newUzer);

		return uzerMapper.toUzerDto(result);
	}

	public UzerDto patch(String username, UzerPatchWrapperDto patch) {
		if (username != patch.getCreds().getUsername() || ValidCreds(patch.getCreds()) == false) {
			return new UzerDto(INVALIDCREDS);
		}

		Uzer uzer = uzerRepository.findByCredentialsUsername(username);
		Profile profile = new Profile(patch.getProfile());
		Profile patchedP = uzer.getProfile();
		if (profile.getEmail() != null)
			patchedP.setEmail(profile.getEmail());
		if (profile.getFirstName() != null)
			patchedP.setFirstName(profile.getFirstName());
		if (profile.getLastName() != null)
			patchedP.setLastName(profile.getLastName());
		if (profile.getPhone() != null)
			patchedP.setPhone(profile.getPhone());

		uzer.setProfile(patchedP);

		uzerRepository.save(uzer);

		return uzerMapper.toUzerDto(uzerRepository.findByCredentialsUsername(username));
	}

	public UzerDto delete(String username, Credentials credentialsDto) {
		if (ValidCreds(credentialsDto) == false)
			return new UzerDto(INVALIDCREDS);
		Uzer uzer = uzerRepository.findByCredentialsUsername(username);
		if (uzer == null)
			return new UzerDto(USERDOESNOTEXIST);
		if (uzer.getDeleted() == true)
			return new UzerDto(ALREADYDELETED); // return some error, already
												// deleted

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
		if (!requestor.follow(uzerToFollow))
			return new String(ALREADYFOLLOWING);

		return new String("OK");
	}

	public String unfollow(String username, CredentialsDto cred) {
		if (ValidCreds(cred) == false)
			return new String(INVALIDCREDS);
		if (exists(username) == false)
			return new String(USERDOESNOTEXIST);

		Uzer uzerToUnfollow = uzerRepository.findByCredentialsUsername(username);
		Uzer requestor = uzerRepository.findByCredentialsUsername(cred.getUsername());
		if (!requestor.unfollow(uzerToUnfollow))
			return new String(ALREADYNOTFOLLOWING);

		return new String("OK");
	}

	public List<TweetDto> getFeed(String username) {
		if (exists(username) == false) {
			List<TweetDto> feedDto = new ArrayList<TweetDto>();
			feedDto.add(new TweetDto(USERDOESNOTEXIST));
			return feedDto;
		}

		ArrayList<Tweet> feed = new ArrayList<Tweet>();

		Uzer uzer = uzerRepository.findByCredentialsUsername(username);

		feed.addAll(uzer.getTweets().stream().filter(x -> x.getDeleted() ? false : true).collect(Collectors.toList()));

		for (Uzer u : uzer.getFollowing()) {
			feed.addAll(u.getTweets().stream().filter(x -> x.getDeleted() ? false : true).collect(Collectors.toList()));
		}

		return feed.stream()
				.sorted(Collections
						.reverseOrder((e1, e2) -> Long.compare(e1.getPosted().getTime(), e2.getPosted().getTime())))
				.map(tweetMapper::toTweetDto).collect(Collectors.toList());
	}

	public List<TweetDto> getTweets(String username) {
		if (exists(username) == false) {
			List<TweetDto> feedDto = new ArrayList<TweetDto>();
			feedDto.add(new TweetDto(USERDOESNOTEXIST));
			return feedDto;
		}

		Uzer uzer = uzerRepository.findByCredentialsUsername(username);

		List<Tweet> list = new ArrayList<Tweet>();
		list.addAll(uzer.getTweets());
		return list.stream().filter(x -> x.getDeleted() ? false : true)
				.sorted(Collections.reverseOrder((e1, e2) -> Long.compare(e1.getPosted().getTime(), e2.getPosted().getTime())))
				.map(tweetMapper::toTweetDto).collect(Collectors.toList());
	}

	public List<UzerDto> getFollowers(String username) {
		List<Uzer> uzers = uzerRepository.findAll().stream()
				.filter(x -> x.getDeleted() ? false : true)
				.collect(Collectors.toList());
		
		Uzer uzerBeingFollowed = uzerRepository.findByCredentialsUsername(username);
		
		List<Uzer> uzersThatAreTheirFollowers = new ArrayList<Uzer>();
		for (Uzer u : uzers)
		{
			if (u.getFollowing().contains(uzerBeingFollowed))
			{
				uzersThatAreTheirFollowers.add(u);
			}
		}
		
		return uzersThatAreTheirFollowers.stream()
				.map(uzerMapper::toUzerDto)
				.collect(Collectors.toList());
	}

	public List<UzerDto> getFollowing(String username) {
		if (exists(username) == false) {
			List<UzerDto> dto = new ArrayList<UzerDto>();
			dto.add(new UzerDto(USERDOESNOTEXIST));
			return dto;
		}
		Uzer uzer = uzerRepository.findByCredentialsUsername(username);

		return uzer.getFollowing().stream().filter(x -> x.getDeleted() ? false : true).map(uzerMapper::toUzerDto)
				.collect(Collectors.toList());
	}

	public List<TweetDto> getMentions(String username) {
		List<Tweet> tweets = tweetRepository.findAll();

		List<Tweet> tweetsThatMentionUsername = new ArrayList<Tweet>();

		for (Tweet t : tweets) {
			if (t.getDeleted() == false && t.getMentionsInThisTweet().contains(username)) {
				tweetsThatMentionUsername.add(t);
			}
		}
		
		return tweetsThatMentionUsername.stream()
				.sorted(Collections.reverseOrder((e1, e2) -> Long.compare(e1.getPosted().getTime(), e2.getPosted().getTime())))
				.map(tweetMapper::toTweetDto).collect(Collectors.toList());
	}

}
