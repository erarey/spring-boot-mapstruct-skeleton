package cooksys.entity;

import java.util.List;
import java.util.Set;

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
	
	@NotNull
	Long firstUsed;
	
	Long lastUsed;
	
	//@ManyToMany // or @ManyToOne?
	@ManyToMany(mappedBy = "hashtagsInThisTweet")
	Set<Tweet> tweetsWithThisHashtag; //sortedSet?

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

	public Long getLastUsed() {
		return lastUsed;
	}
	
	public void setFirstUsed(Long firstUsed)
	{// can only be set once.
		if (this.firstUsed == null && firstUsed != null) 
			this.firstUsed = firstUsed; 
	}
	public void setLastUsed(Long lastUsed) {
		this.lastUsed = lastUsed;
	}

	public Set<Tweet> getTweetsWithThisHashtag() {
		return tweetsWithThisHashtag;
	}

	public void setTweetsWithThisTag(Set<Tweet> tweetsWithThisHashtag) {
		this.tweetsWithThisHashtag = tweetsWithThisHashtag;
	}
	
	
}
