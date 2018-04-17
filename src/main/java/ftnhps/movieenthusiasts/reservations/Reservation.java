package ftnhps.movieenthusiasts.reservations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import ftnhps.movieenthusiasts.DateAndTime.DateAndTime;
import ftnhps.movieenthusiasts.users.User;


@Entity
public class Reservation {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Min(0)
	@Max(5)
	private int ambientRating;
	
	@Min(0)
	@Max(5)
	private int projectionRating;
	
	@Min(0)
	private double priceWithDiscount;
	
	@Min(0)
	private double dicount;
	
	
	@ManyToOne(optional = false)
	private DateAndTime dateTime;
	
	/*
	 * Ako nema korisnika onda je to ona karta za brzu rezervaciju
	 */
	@ManyToOne(optional = true)
	private User user;
	
	public Reservation () {}

	public Reservation(Long id, int ambientRating, int projectionRating, double priceWithDiscount, double dicount,
			DateAndTime dateTime, User user) {
		super();
		this.id = id;
		this.ambientRating = ambientRating;
		this.projectionRating = projectionRating;
		this.priceWithDiscount = priceWithDiscount;
		this.dicount = dicount;
		this.dateTime = dateTime;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
