package cooksys.service;

import java.util.List;

import cooksys.dto.CredentialsDto;
import cooksys.dto.TweetDto;
import cooksys.dto.UzerDto;
import cooksys.dto.UzerPatchWrapperDto;
import cooksys.repository.UzerRepository;
import org.springframework.stereotype.Service;

@Service
public class UzerService {

	private UzerRepository uzerRepository;
	
	public List<UzerDto> index() {
		// TODO Auto-generated method stub
		return null;
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

	public UzerDto post(UzerDto dto) {
		// TODO Auto-generated method stub
		return null;
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
