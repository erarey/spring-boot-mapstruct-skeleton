package cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import cooksys.entity.Hashtag;
import cooksys.entity.Uzer;
import cooksys.entity.embeddable.Credentials;

@Repository
public interface UzerRepository extends JpaRepository<Uzer, Long>{
	Uzer findByCredentialsUsername(String username);
	
	Uzer findByProfileEmail(String email);
	
	Uzer findByCredentials(Credentials creds);
}
