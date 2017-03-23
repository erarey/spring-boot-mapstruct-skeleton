package cooksys.mapper;

import java.sql.Timestamp;
import java.util.Set;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import cooksys.dto.TweetDto;
import cooksys.entity.Hashtag;
import cooksys.entity.Tweet;


//@Mapper(componentModel = "spring") //uses ...
@Component
public class TweetMapper {

	public Tweet toTweet(TweetDto tweetDto, Set<String> hashtags, Set<String> mentions, Boolean deleted)
	{
		if (tweetDto == null) return null;
		
		Tweet t = new Tweet();
		t.setId(tweetDto.getId());
		t.setAuthor(tweetDto.getAuthor());
		t.setContent(tweetDto.getContent());
		t.setDeleted(deleted);
		t.setHashtagsInThisTweet(hashtags);
		t.setInReplyTo(tweetDto.getInReplyTo());
		t.setRepostOf(tweetDto.getRepostOf());
		t.setPosted(new Timestamp(tweetDto.getPosted()));
		
		return t;
	}
	
	public Tweet toNewTweet(TweetDto tweetDto, Set<String> hashtags, Set<String> mentions)
	{
		//timestamp should transfer as null, allowing the database to change it... maybe
		
		return toTweet(tweetDto, hashtags, mentions, false);
	}
	
	public TweetDto toTweetDto(Tweet t)
	{
		if (t == null) return null;
		
		TweetDto dto = new TweetDto();
		dto.setId(t.getId());
		dto.setAuthor(t.getAuthor());
		dto.setContent(t.getContent());
		dto.setInReplyTo(t.getInReplyTo());
		dto.setRepostOf(t.getRepostOf());
		dto.setPosted(t.getPosted().getTime());
		
		return dto;
	}
	
}
