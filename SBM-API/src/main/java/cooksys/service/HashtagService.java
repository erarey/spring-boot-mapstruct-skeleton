package cooksys.service;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cooksys.dto.HashtagDto;
import cooksys.dto.TweetDto;
import cooksys.entity.Tweet;
import cooksys.mapper.HashtagMapper;
import cooksys.mapper.TweetMapper;
import cooksys.mapper.UzerMapper;
import cooksys.repository.HashtagRepository;
import cooksys.repository.TweetRepository;
import cooksys.repository.UzerRepository;

@Service
public class HashtagService {

	private TweetRepository tweetRepository;
	private TweetMapper tweetMapper;
	private HashtagMapper hashtagMapper;
	private HashtagRepository hashtagRepository;
	
	HashtagService(TweetMapper tweetMapper, TweetRepository tweetRepository, HashtagRepository hashtagRepository, HashtagMapper hashtagMapper) {
		super();
		this.tweetMapper = tweetMapper;
		this.tweetRepository = tweetRepository;
		this.hashtagRepository = hashtagRepository;
		this.hashtagMapper = hashtagMapper;
	}
	public Boolean exists(String label) {
		if (hashtagRepository.findByLabeld(label) == null) return false;
		
		else return true;
	}

	public List<HashtagDto> index() {
		return hashtagRepository.findAll().stream()
				.sorted(Collections.reverseOrder((e1, e2) -> Long.compare(e1.getFirstUsed().getTime(), e2.getFirstUsed().getTime())))
				.map(hashtagMapper::toHashtagDto).collect(Collectors.toList());
	}

	public List<TweetDto> getTweetsByLabel(String label) {
		// TODO Auto-generated method stub
		return null;
	}

}
