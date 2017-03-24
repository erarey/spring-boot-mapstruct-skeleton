package cooksys.service;
import org.springframework.stereotype.Service;

import cooksys.repository.HashtagRepository;

@Service
public class HashtagService {

	private HashtagRepository hashtagRepository;
	
	public Boolean exists(String label) {
		if (hashtagRepository.findByLabeld(label) == null) return false;
		
		else return true;
	}

}
