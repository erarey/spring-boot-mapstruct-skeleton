package cooksys.service;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

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
		if (username != patch.getCreds().getUsername())
		{
			return null;
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

	public UzerDto delete(String username) {
		Uzer uzer = uzerRepository.findByCredentialsUsername(username);
		if (uzer.getDeleted() == true) return null; //return some error, already deleted
		
		uzer.setDeleted(true);
		
		uzerRepository.save(uzer);
		
		return uzerMapper.toUzerDto(uzer);
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
