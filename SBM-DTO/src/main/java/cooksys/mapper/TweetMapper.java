package cooksys.mapper;

import org.mapstruct.Mapper;

import cooksys.dto.TweetDto;
import cooksys.entity.Tweet;


@Mapper(componentModel = "spring") //uses ...
public interface TweetMapper {

	Tweet toTweet(TweetDto tweetDto);
	
	TweetDto toTweetDto(Tweet tweet);
	
}
