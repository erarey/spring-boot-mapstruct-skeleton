package cooksys.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Hashtag {
	//list of tweets where used?
	
	@Id
	@GeneratedValue
	Long id;
	
	@NotNull
	String labeld;
	
	@Column(name = "joined", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	Timestamp firstUsed;
	
	Timestamp lastUsed;
	
	//@ManyToMany // or @ManyToOne?
	//@ManyToMany(mappedBy = "hashtagsInThisTweet")
	@ElementCollection
	Set<Long> tweetsWithThisHashtag;

	//public Hashtag(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return labeld;
	}

	public void setLabel(String label) {
		this.labeld = label;
	}

	public Timestamp getLastUsed() {
		return lastUsed;
	}
	
	public void setFirstUsed(Timestamp firstUsed)
	{// can only be set once.
		if (this.firstUsed == null && firstUsed != null) 
			this.firstUsed = firstUsed; 
	}
	public void setLastUsed(Timestamp lastUsed) {
		this.lastUsed = lastUsed;
	}

	public Set<Long> getTweetsWithThisHashtag() {
		return tweetsWithThisHashtag;
	}

	public void setTweetsWithThisTag(Set<Long> tweetsWithThisHashtag) {
		this.tweetsWithThisHashtag = tweetsWithThisHashtag;
	}
	
	
}
