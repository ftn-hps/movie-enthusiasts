package ftnhps.movieenthusiasts.fanzone.reservation;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ftnhps.movieenthusiasts.fanzone.propnew.PropNew;
import ftnhps.movieenthusiasts.users.User;

@Entity
public class PropReservation {

	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private PropNew propNew;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private User user;
	
	@NotNull
	@Min(1)
	private int quantity;
	
	@NotNull
	private Boolean canceled;
	
	public PropReservation() {}

	public PropReservation(PropNew propNew, User user, int quantity) {
		this.propNew = propNew;
		this.user = user;
		this.quantity = quantity;
		this.canceled = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PropNew getPropNew() {
		return propNew;
	}

	public void setPropNew(PropNew propNew) {
		this.propNew = propNew;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Boolean getCanceled() {
		return canceled;
	}

	public void setCanceled(Boolean canceled) {
		this.canceled = canceled;
	}

}
