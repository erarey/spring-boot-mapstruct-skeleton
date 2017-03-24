package cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cooksys.entity.Hashtag;
import cooksys.entity.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long>{
	
	Tweet findByAuthor(String author);
	
	Tweet findById(Long id);
	
	Tweet findByInReplyTo(Long id);
	
	Tweet findByRepostOf(Long id);
	
}
