package cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cooksys.entity.Hashtag;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
	
	Hashtag findByLabeld(String labeld);
	
	Hashtag findById(Long id);
}
