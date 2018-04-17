package ftnhps.movieenthusiasts.hall;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import ftnhps.movieenthusiasts.places.Place;

@Entity
public class Hall {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	private String name;
	
	@Min(1)
	private int rows;
	
	@Min(1)
	private int columns;
	
    /*
     * This string is 1D representation of 2D matrix which gives information 
     * about layout in Hall.
     * Meaning:
     * 		x - seat in NOT available for reservations 
     * 		v - VIP seat
     * 		o - seat is available for reservations
     */
	private String layout;
	
	@ManyToOne(optional = false)
	private Place place;

	public Hall() {}
	public Hall(String name, int rows, int columns, String layout, Place place) {
		super();
		this.name = name;
		this.rows = rows;
		this.columns = columns;
		this.layout = layout;
		this.place = place;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}
	
	
}
