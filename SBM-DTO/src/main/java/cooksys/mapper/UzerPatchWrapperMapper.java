package cooksys.mapper;

import org.mapstruct.Mapper;

import cooksys.dto.UzerDto;
import cooksys.dto.UzerPatchWrapperDto;
import cooksys.entity.Uzer;
import cooksys.wrapper.UzerPatchWrapper;

@Mapper(componentModel = "spring") //uses ...
public interface UzerPatchWrapperMapper {

	UzerPatchWrapper toUzerPatchWrapper(UzerPatchWrapperDto uzerPatchWrapperDto);
	
	UzerPatchWrapperDto toUzerPatchWrapperDto(UzerPatchWrapper uzerPatchWrapper);
	
}