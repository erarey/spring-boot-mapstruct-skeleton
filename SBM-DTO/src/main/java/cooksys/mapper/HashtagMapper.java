package cooksys.mapper;

import org.mapstruct.Mapper;

import cooksys.dto.HashtagDto;
import cooksys.entity.Hashtag;

@Mapper(componentModel = "spring")
public interface HashtagMapper {
	Hashtag toHashtag(HashtagDto hashtagDto);
	
	HashtagDto toHashtagDto(Hashtag hashtag);
}
