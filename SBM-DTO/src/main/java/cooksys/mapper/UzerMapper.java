package cooksys.mapper;

import org.mapstruct.Mapper;

import cooksys.dto.UzerDto;
import cooksys.entity.Uzer;

@Mapper(componentModel = "spring") //uses ...
public interface UzerMapper {

	Uzer toUzer(UzerDto uzerDto);
	
	UzerDto toUzerDto(Uzer uzer);
	
}
