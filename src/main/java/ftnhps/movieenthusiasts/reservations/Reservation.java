package ftnhps.movieenthusiasts.reservations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ftnhps.movieenthusiasts.DateAndTime.DateAndTime;
import ftnhps.movieenthusiasts.users.User;


@Entity
public class Reservation {
	
	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@Version
	private Long version;
	
	@Min(0)
	@Max(5)
	private Integer ambientRating;
	
	@Min(0)
	@Max(5)
	private Integer projectionRating;
	
	@Min(0)
	private double priceWithDiscount;
	
	@Min(0)
	private double dicount;
	
	@ManyToOne(optional = false)
	private DateAndTime dateTime;
	
	@Min(0)
	private int seat;
	
	private boolean isRewarded = false;
	
	/*
	 * Ako nema korisnika onda je to ona karta za brzu rezervaciju
	 */
	@ManyToOne(optional = true)
	private User user;
	
	public Reservation () {}

	public Reservation(double dicount,
			DateAndTime dateTime,
			int seat,
			User user) {
		super();
		this.priceWithDiscount = dateTime.getProjection().getPrice() - dateTime.getProjection().getPrice()*(dicount/100);
		this.dicount = dicount;
		this.dateTime = dateTime;
		this.seat = seat;
		this.user = user;
		
		this.ambientRating = 0;
		this.projectionRating = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAmbientRating() {
		return ambientRating;
	}

	public void setAmbientRating(int ambientRating) {
		this.ambientRating = ambientRating;
	}

	public int getProjectionRating() {
		return projectionRating;
	}

	public void setProjectionRating(int projectionRating) {
		this.projectionRating = projectionRating;
	}

	public double getPriceWithDiscount() {
		return priceWithDiscount;
	}

	public void setPriceWithDiscount(double priceWithDiscount) {
		this.priceWithDiscount = priceWithDiscount;
	}

	public double getDicount() {
		return dicount;
	}

	public void setDicount(double dicount) {
		this.dicount = dicount;
	}

	public DateAndTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateAndTime dateTime) {
		this.dateTime = dateTime;
	}

	public int getSeat() {
		return seat;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public void setAmbientRating(Integer ambientRating) {
		this.ambientRating = ambientRating;
	}

	public void setProjectionRating(Integer projectionRating) {
		this.projectionRating = projectionRating;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isRewarded() {
		return isRewarded;
	}

	public void setRewarded(boolean isRewarded) {
		this.isRewarded = isRewarded;
	}
	
	
}
