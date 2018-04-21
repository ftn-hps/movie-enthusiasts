package ftnhps.movieenthusiasts.places;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Place {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Version
	private Long version;
	
	@Enumerated(EnumType.STRING)
	private PlaceType type;
	
	@Pattern(regexp = "(?U)[\\p{Alpha}\\h]+")
	@NotBlank
	private String name;
	
	@Max(5)
	@Min(1)
	private int rating;
	
	@Pattern(regexp = "(?U)[\\p{Alpha}\\h]+")
	@NotBlank
	private String address;
	
	@NotBlank
	private String description;
	
	@Min(-90)
	@Max(90)
	private Double lat;
	
	@Min(-180)
	@Max(180)
	private Double lng;

	public Place() {}

	public Place(PlaceType type,
			String name,
			int rating,
			String address,
			String description,
			Double lat,
			Double lng) {
		this.type = type;
		this.name = name;
		this.rating = rating;
		this.address = address;
		this.description = description;
		this.lat = lat;
		this.lng = lng;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public PlaceType getType() {
		return type;
	}

	public void setType(PlaceType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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
		this.lat = lng;
	}
	
}
