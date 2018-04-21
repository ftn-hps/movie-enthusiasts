package ftnhps.movieenthusiasts.fanzone.propnew;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ftnhps.movieenthusiasts.places.Place;

@Entity
public class PropNew {
	
	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@Version
	private Long version;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Place place;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@NotNull
	private Boolean deleted;
	
	private String imagePath;
	
	public PropNew() {}
	
	public PropNew(Place place, String name, String description, String imagePath) {
		this.place = place;
		this.name = name;
		this.description = description;
		this.imagePath = imagePath;
		this.deleted = false;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}
