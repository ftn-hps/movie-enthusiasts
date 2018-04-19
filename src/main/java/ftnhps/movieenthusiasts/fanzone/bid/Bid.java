package ftnhps.movieenthusiasts.fanzone.bid;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ftnhps.movieenthusiasts.fanzone.propused.PropUsed;
import ftnhps.movieenthusiasts.users.User;

@Entity
public class Bid {
	
	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private PropUsed propUsed;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private User user;

	@NotNull
	@Min(0)
	private Double bid;
	
	public Bid() {}

	public Bid(PropUsed propUsed, User user, Double bid) {
		this.propUsed = propUsed;
		this.user = user;
		this.bid = bid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PropUsed getPropUsed() {
		return propUsed;
	}

	public void setPropUsed(PropUsed propUsed) {
		this.propUsed = propUsed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

}
