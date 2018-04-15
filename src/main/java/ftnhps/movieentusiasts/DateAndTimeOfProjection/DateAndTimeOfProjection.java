package ftnhps.movieentusiasts.DateAndTimeOfProjection;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.projections.Projection;

@Entity
public class DateAndTimeOfProjection {

	@Id
	@JsonProperty(access = Access.READ_ONLY)
	@GeneratedValue
	private Long id;
	
	private LocalDate date;
	
	private LocalTime time;
	
	/*
	 * This field is 1D representation of 2D matrix of seats in hall
	 * Meaning:
	 * 		r - reserved
	 * 		o - available seat
	 * 		v - vip 
	 * 		x - unabailable seat
	 */
	private String reservationLayout;
	
	@ManyToOne(optional = false)
	private Projection projection;
	
	
	@ManyToOne(optional = false)
	private Hall hall;


	public DateAndTimeOfProjection(Long id, LocalDate date, LocalTime time, String reservationLayout,
			Projection projection, Hall hall) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.reservationLayout = reservationLayout;
		this.projection = projection;
		this.hall = hall;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public LocalTime getTime() {
		return time;
	}


	public void setTime(LocalTime time) {
		this.time = time;
	}


	public String getReservationLayout() {
		return reservationLayout;
	}


	public void setReservationLayout(String reservationLayout) {
		this.reservationLayout = reservationLayout;
	}


	public Projection getProjection() {
		return projection;
	}


	public void setProjection(Projection projection) {
		this.projection = projection;
	}


	public Hall getHall() {
		return hall;
	}


	public void setHall(Hall hall) {
		this.hall = hall;
	}
	
	
	
}
