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
	
	
}
