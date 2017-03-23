package cooksys.mapper;

import org.mapstruct.Mapper;

import cooksys.dto.CredentialsDto;
import cooksys.entity.embeddable.Credentials;

@Mapper(componentModel = "spring") // uses ...
public interface CredentialsMapper {

	Credentials toCredentials(CredentialsDto credentialsDto);

	CredentialsDto toCredentialsDto(Credentials Credentials);

}
