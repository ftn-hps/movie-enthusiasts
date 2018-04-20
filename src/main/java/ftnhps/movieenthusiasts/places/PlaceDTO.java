package ftnhps.movieenthusiasts.places;

public class PlaceDTO {

	private String name;
	
	private String address;
	
	private String description;
	
	private String type;
	
	private Double lat;
	
	private Double lng;
	
	public PlaceDTO() {}

	public PlaceDTO(String name, String address, String description, String type, Double lat, Double lng) {
		this.name = name;
		this.address = address;
		this.description = description;
		this.type = type;
		this.lat = lat;
		this.lng = lng;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}
	
}
