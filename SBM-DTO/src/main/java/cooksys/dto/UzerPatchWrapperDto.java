package cooksys.dto;

import cooksys.entity.embeddable.Credentials;
import cooksys.entity.embeddable.Profile;

public class UzerPatchWrapperDto {
	
	Credentials creds;
	
	Profile profile;

	public Credentials getCreds() {
		return creds;
	}

	public void setCreds(Credentials creds) {
		this.creds = creds;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creds == null) ? 0 : creds.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
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
		UzerPatchWrapperDto other = (UzerPatchWrapperDto) obj;
		if (creds == null) {
			if (other.creds != null)
				return false;
		} else if (!creds.equals(other.creds))
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		return true;
	}
	
	
}
