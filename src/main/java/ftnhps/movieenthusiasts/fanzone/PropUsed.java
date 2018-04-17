package ftnhps.movieenthusiasts.fanzone;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class PropUsed {
	
	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@NotNull
	private Long userId;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@NotNull
	private LocalDateTime date;
	
	@NotNull
	private Boolean approved;
	
	private String imagePath;
	
	public PropUsed() {}

	public PropUsed(Long userId, String name, String description, LocalDateTime date, String imagePath) {
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.date = date;
		this.approved = false;
		this.imagePath = imagePath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
