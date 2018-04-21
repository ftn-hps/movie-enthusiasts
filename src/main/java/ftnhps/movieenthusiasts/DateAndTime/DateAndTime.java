package ftnhps.movieenthusiasts.DateAndTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.projections.Projection;

/**
 * @author horva
 *
 */
@Entity
public class DateAndTime {

	@Id
	@JsonProperty(access = Access.READ_ONLY)
	@GeneratedValue
	private Long id;
	
	@Version
	private Long version;
	
	@Min(100)
	private Long timeStamp;
	
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

	public DateAndTime() {}
	public DateAndTime(Long timeStamp, String reservationLayout,
			Projection projection, Hall hall) {
		super();
		this.timeStamp = timeStamp;
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


	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getReservationLayout() {
		return reservationLayout;
	}


	public void setReservationLayout(String reservationLayout) {
		this.reservationLayout = reservationLayout;
	}


	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
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
