package cooksys.mapper;

import java.sql.Timestamp;

import org.mapstruct.Mapper;

import cooksys.dto.UzerDto;
import cooksys.entity.Uzer;
import cooksys.entity.embeddable.Credentials;

@Mapper(componentModel = "spring") //uses ...
public interface UzerMapper {
	
	
	default public Uzer toUzer(UzerDto uzerDto, Credentials credentials, Timestamp joined)
	{
		//if credentials are valid
		Uzer uzer = new Uzer();
		uzer.setCredentials(credentials);
		uzer.setJoined(joined);
		uzer.setProfile(uzerDto.getProfile());
		
		return uzer;
	}
	
	
	default public UzerDto toUzerDto(Uzer uzer)
	{
		UzerDto dto = new UzerDto();
		dto.setUsername(uzer.getCredentials().getUsername());
		
		dto.setProfile(uzer.getProfile());
		
		return dto;
	}
	
}
