package cooksys.service;

import java.util.List;
import java.util.stream.Collectors;

import cooksys.dto.CredentialsDto;
import cooksys.dto.TweetDto;
import cooksys.dto.UzerDto;
import cooksys.dto.UzerPatchWrapperDto;
import cooksys.entity.Uzer;
import cooksys.entity.embeddable.Profile;
import cooksys.mapper.UzerMapper;
import cooksys.repository.UzerRepository;
import org.springframework.stereotype.Service;


@Service
public class UzerService {

	private UzerRepository uzerRepository;
	private UzerMapper uzerMapper;
	
	UzerService(UzerMapper uzerMapper, UzerRepository uzerRepository)
	{
		super();
		this.uzerMapper = uzerMapper;
		this.uzerRepository = uzerRepository;
	}
	
	public List<UzerDto> index() {
		List<UzerDto> dtos = uzerRepository.findAll().stream()
				.map(uzerMapper::toUzerDto)
				.collect(Collectors.toList());
		
		//System.out.println(dtos.get(0).getUsername());
		
		return dtos;
	}

	public boolean exists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean available(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	public UzerDto getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public UzerDto post(UzerPatchWrapperDto dto) {
		//Manual mapper
		Uzer newUzer = new Uzer();
		System.out.println("username from dto " + dto.getCreds().getUsername());
		System.out.println(dto.getCreds().getPassword());
		
		newUzer.setUsername(new String(dto.getCreds().getUsername()));
		newUzer.setPassword(new String(dto.getCreds().getPassword()));
		
		System.out.println(newUzer.getUsername());
		
		newUzer.setProfile(new Profile(dto.getProfile()));
		
		Uzer result = uzerRepository.save(newUzer);
		
		System.out.println("new user's timestamp was: " + result.getJoined());
		//if it was successful, return the Dto of the user just created.
		
		UzerDto dto2 = uzerMapper.toUzerDto(result);
		
		System.out.println(dto2.getUsername());
	}

	public UzerDto patch(String username, UzerPatchWrapperDto patch) {
		// TODO Auto-generated method stub
		return null;
	}

	public UzerDto delete(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public String follow(String username, CredentialsDto cred) {
		// TODO Auto-generated method stub
		return null;
	}

	public String unfollow(String username, CredentialsDto cred) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TweetDto> getFeed(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TweetDto> getTweets(String username) {
		// TODO Auto-generated method stub
		return null;
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
