package cooksys.entity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Tweet {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@ManyToOne
	private Uzer author;
	
	//private Long posted;
	
	private String content;
	
	private Boolean deleted = false;
	
	@Column(name = "posted", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp posted;
	
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@ManyToOne
	Tweet inReplyTo;
	
	@ManyToOne
	Tweet repostOf;
	
	@ElementCollection
	Set<String> hashtagsInThisTweet;

	@ElementCollection
	Set<String> mentionsInThisTweet;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Uzer getAuthor() {
		return author;
	}

	public void setAuthor(Uzer author) {
		this.author = author;
	}

	public Timestamp getPosted() {
		return posted;
	}

	public void setPosted(Timestamp posted) {
		this.posted = posted;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Tweet getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(Tweet inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	public Tweet getRepostOf() {
		return repostOf;
	}

	public void setRepostOf(Tweet repostOf) {
		this.repostOf = repostOf;
	}
	
	


	public Set<String> getHashtagsInThisTweet() {
		return hashtagsInThisTweet;
	}

	public void setHashtagsInThisTweet(Set<String> hashtagsInThisTweet) {
		this.hashtagsInThisTweet = hashtagsInThisTweet;
	}

	public Set<String> getMentionsInThisTweet() {
		return mentionsInThisTweet;
	}

	public void setMentionsInThisTweet(Set<String> mentionsInThisTweet) {
		this.mentionsInThisTweet = mentionsInThisTweet;
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
		Tweet other = (Tweet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
