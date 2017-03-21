package cooksys.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Tag {
	//list of tweets where used?
	
	@Id
	@GeneratedValue
	Long id;
	
	@NotNull
	String label;
	
	@NotNull
	Long firstUsed;
	
	Long lastUsed;
	
	@ManyToMany // or @ManyToOne?
	Set<Tweet> tweetsWithThisTag; //sortedSet?

	Tag(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public Set<Tweet> getTweetsWithThisTag() {
		return tweetsWithThisTag;
	}

	public void setTweetsWithThisTag(Set<Tweet> tweetsWithThisTag) {
		this.tweetsWithThisTag = tweetsWithThisTag;
	}
	
	
}
