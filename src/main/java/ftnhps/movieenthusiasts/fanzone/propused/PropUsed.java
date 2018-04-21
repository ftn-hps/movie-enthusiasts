package ftnhps.movieenthusiasts.fanzone.propused;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ftnhps.movieenthusiasts.fanzone.bid.Bid;
import ftnhps.movieenthusiasts.users.User;

@Entity
public class PropUsed {
	
	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@Version
	private Long version;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private User user;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@NotNull
	private LocalDateTime date;
	
	private Boolean approved;
	
	@OneToOne(optional = true)
	private Bid acceptedBid;
	
	private String imagePath;
	
	public PropUsed() {}

	public PropUsed(User user, String name, String description, LocalDateTime date, String imagePath) {
		this.user = user;
		this.name = name;
		this.description = description;
		this.date = date;
		this.approved = false;
		this.imagePath = imagePath;
		this.acceptedBid = null;
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

	public User getUser() {
		return user;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Bid getAcceptedBid() {
		return acceptedBid;
	}

	public void setAcceptedBid(Bid acceptedBid) {
		this.acceptedBid = acceptedBid;
	}
	
}
