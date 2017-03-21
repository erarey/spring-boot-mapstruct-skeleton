package cooksys.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import cooksys.entity.embeddable.Profile;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Profile profile;
	
	@NotNull
	private Long joined;
	
	@OneToMany(mappedBy = "author")
	Set<Tweet> tweets; //= new HashSet<>();
	
	//Set<Tweet> mentionedIn;
	
	@ManyToMany
	Set<User> following;
	
	@ManyToMany
	Set<User> followers;
	
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
	public Set<User> getFollowing() {
		return following;
	}

	public void setFollowing(Set<User> following) {
		this.following = following;
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
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

	public Long getJoined() {
		return joined;
	}

	public void setJoined(Long joined) {
		this.joined = joined;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
