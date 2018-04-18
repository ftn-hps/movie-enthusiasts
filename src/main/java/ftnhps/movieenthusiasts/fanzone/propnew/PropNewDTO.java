package ftnhps.movieenthusiasts.fanzone.propnew;

public class PropNewDTO {
	
	private Long placeId;
	
	private String name;
	
	private String description;
	
	public PropNewDTO() {}

	public PropNewDTO(Long placeId, String name, String description) {
		this.placeId = placeId;
		this.name = name;
		this.description = description;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
