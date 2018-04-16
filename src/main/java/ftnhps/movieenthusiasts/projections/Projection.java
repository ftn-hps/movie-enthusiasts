package ftnhps.movieenthusiasts.projections;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import javax.persistence.CascadeType;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ftnhps.movieenthusiasts.places.Place;


@Entity
public class Projection {
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Place place;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String listOfActers;
	
	@NotBlank
	private String genre;
	
	@NotBlank
	private String producer;
	
	private int duration;
	
	private String imagePath;
	
	private int averateRating;
	
	@NotBlank
	private String shortDescription;
	
	@Min(0)
	private double price;

	public Projection ( ) {}
	
	public Projection(Place place,
			String name,
			String listOfActers,
			String genre,
			String producer,
			int duration,
			String imagePath,
			int averateRating,
			String shortDescription,
			double price) {
		super();
		this.place = place;
		this.name = name;
		this.listOfActers = listOfActers;
		this.genre = genre;
		this.producer = producer;
		this.duration = duration;
		this.imagePath = imagePath;
		this.averateRating = averateRating;
		this.shortDescription = shortDescription;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getListOfActers() {
		return listOfActers;
	}

	public void setListOfActers(String listOfActers) {
		this.listOfActers = listOfActers;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getAverateRating() {
		return averateRating;
	}

	public void setAverateRating(int averateRating) {
		this.averateRating = averateRating;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
