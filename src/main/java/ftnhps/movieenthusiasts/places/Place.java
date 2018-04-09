package ftnhps.movieenthusiasts.places;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Place {
	
	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
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
	
	@Pattern(regexp = "(?U)[\\p{Alpha}\\s]+")
	@NotBlank
	private String description;

	public Place() {}

	public Place(PlaceType type,
			String name,
			int rating,
			String address,
			String description) {
		this.type = type;
		this.name = name;
		this.rating = rating;
		this.address = address;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
