package cooksys.mapper;

import java.sql.Timestamp;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import cooksys.dto.UzerDto;
import cooksys.entity.Uzer;
import cooksys.entity.embeddable.Credentials;
import cooksys.entity.embeddable.Profile;

//@Mapper(componentModel = "spring") //uses ...
@Component
public class UzerMapper {
	
	
	public Uzer toUzer(UzerDto uzerDto, Credentials credentials, Timestamp joined)
	{
		//if credentials are valid
		Uzer uzer = new Uzer();
		uzer.setCredentials(credentials);
		uzer.setJoined(joined);
		uzer.setProfile(uzerDto.getProfile());
		
		return uzer;
	}
	
	public Uzer toNewUzer(Profile profile, Credentials credentials)
	{
		//if credentials are valid
		Uzer uzer = new Uzer();
		uzer.setCredentials(credentials);
		uzer.setProfile(profile);
		
		return uzer;
	}
	
	
	public UzerDto toUzerDto(Uzer uzer)
	{
		UzerDto dto = new UzerDto();
		dto.setUsername(uzer.getCredentials().getUsername());
		
		dto.setProfile(uzer.getProfile());
		
		return dto;
	}
	
}
