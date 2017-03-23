package cooksys.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import cooksys.entity.embeddable.Credentials;
import cooksys.entity.embeddable.Profile;

@Entity
public class Uzer {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Credentials credentials;
	
	Boolean deleted = false;

	private Profile profile;
	
	@Column(name = "joined", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private java.sql.Timestamp joined;
	

	@OneToMany(mappedBy = "author")
	Set<Tweet> tweets; //= new HashSet<>();
	
	//Set<Tweet> mentionedIn;
	
	@ManyToMany
	Set<Uzer> following;
	
	//@ManyToMany
	//Set<Uzer> followers;
	
	public boolean follow(Uzer uzer)
	{
		if (following.contains(uzer)) return false;
		
		following.add(uzer);
		
		return true;
	}
	
	public boolean unfollow(Uzer uzer)
	{
		if (following.contains(uzer)) return false;
		
		following.add(uzer);
		
		return true;
	}
	
	
	public Set<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(Set<Tweet> tweets) {
		this.tweets = tweets;
	}

	/*public Set<Tweet> getMentionedIn() {
		return mentionedIn;
	}

	public void setMentionedIn(Set<Tweet> mentionedIn) {
		this.mentionedIn = mentionedIn;
	}
	 */
	public Set<Uzer> getFollowing() {
		return following;
	}

	public void setFollowing(Set<Uzer> following) {
		this.following = following;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Timestamp getJoined() {
		return joined;
	}

	public void setJoined(Timestamp joined) {
		this.joined = joined;
	}
	
	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Uzer other = (Uzer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
