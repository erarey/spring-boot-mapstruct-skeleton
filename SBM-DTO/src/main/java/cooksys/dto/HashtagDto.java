package cooksys.dto;

//import javax.persistence.Transient;

//import net.minidev.json.annotate.JsonIgnore;

public class HashtagDto implements DtoMarker {

	Long id;
	
	String label;
	
	Long firstUsed;
	
	Long lastUsed;
	
	//@Transient
	//@JsonIgnore
	private String serverComm;
	
	public HashtagDto(){}
	
	public HashtagDto(String serverComm){this.label = serverComm; this.id = null;}
	
	public String getServerComm() {
		return serverComm;
	}

	public void setServerComm(String serverComm) {
		this.serverComm = serverComm;
	}
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

	public Long getFirstUsed() {
		return firstUsed;
	}

	public void setFirstUsed(Long firstUsed) {
		this.firstUsed = firstUsed;
	}

	public Long getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Long lastUsed) {
		this.lastUsed = lastUsed;
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
		HashtagDto other = (HashtagDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
