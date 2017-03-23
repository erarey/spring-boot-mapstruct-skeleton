package cooksys.dto;

public class ReDuple {
	String response;
	
	DtoMarker dto;

	public ReDuple(String response, DtoMarker dto)
	{
		this.response = response;
		this.dto = dto;
	}
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public DtoMarker getDto() {
		return dto;
	}

	public void setDto(DtoMarker dto) {
		this.dto = dto;
	}
	
	
}
