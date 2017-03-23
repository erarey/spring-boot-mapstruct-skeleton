package cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cooksys.entity.Hashtag;
import cooksys.entity.Uzer;

@Repository
public interface UzerRepository extends JpaRepository<Uzer, Long>{
	Uzer findByCredentialsUsername(String username);
	
	Uzer findByProfileEmail(String email);
}
