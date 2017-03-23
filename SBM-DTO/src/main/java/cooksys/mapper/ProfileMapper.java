package cooksys.mapper;

import org.mapstruct.Mapper;

import cooksys.dto.ProfileDto;
import cooksys.entity.embeddable.Profile;

@Mapper(componentModel = "spring") //uses ...
public interface ProfileMapper {

	Profile toProfile(ProfileDto profileDto);
	
	ProfileDto toProfileDto(Profile profile);
	
}