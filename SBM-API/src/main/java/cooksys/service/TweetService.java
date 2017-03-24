package cooksys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cooksys.dto.ContextDto;
import cooksys.dto.TweetDto;
import cooksys.dto.TweetWrapperDto;
import cooksys.entity.Hashtag;
import cooksys.entity.Uzer;
import cooksys.entity.embeddable.Credentials;

@Service
public class TweetService {

	public List<TweetDto> index() {
		// TODO Auto-generated method stub
		return null;
	}

	public TweetDto post(TweetWrapperDto wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	public TweetDto getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public TweetDto delete(Long id, Credentials creds) {
		// TODO Auto-generated method stub
		return null;
	}

	public TweetDto reply(Long id, TweetWrapperDto wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	public String like(Long id, Credentials creds) {
		// TODO Auto-generated method stub
		return null;
	}

	public TweetDto repost(Long id, Credentials creds) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Hashtag> getTags(Long id) {
		// TODO Auto-generated method stub
		return null;
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
