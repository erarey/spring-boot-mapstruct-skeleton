package cooksys.dto;

import cooksys.entity.embeddable.Credentials;

public class TweetWrapperDto {

	Credentials creds;
	
	String content;

	public Credentials getCreds() {
		return creds;
	}

	public void setCreds(Credentials creds) {
		this.creds = creds;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((creds == null) ? 0 : creds.hashCode());
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
		TweetWrapperDto other = (TweetWrapperDto) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (creds == null) {
			if (other.creds != null)
				return false;
		} else if (!creds.equals(other.creds))
			return false;
		return true;
	}
	
	
}
