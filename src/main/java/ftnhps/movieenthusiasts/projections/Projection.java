package ftnhps.movieenthusiasts.projections;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import ftnhps.movieenthusiasts.places.Place;


@Entity
public class Projection {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Version
	private Long version;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Place place;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String listOfActors;
	
	@NotBlank
	private String genre;
	
	@NotBlank
	private String producer;
	
	private int duration;
	
	private String imagePath;
	
	private int averageRating;
	
	@NotBlank
	private String shortDescription;
	
	@Min(0)
	private double price;

	public Projection ( ) {}
	
	public Projection(Place place,
			String name,
			String listOfActors,
			String genre,
			String producer,
			int duration,
			String imagePath,
			int averageRating,
			String shortDescription,
			double price) {
		super();
		this.place = place;
		this.name = name;
		this.listOfActors = listOfActors;
		this.genre = genre;
		this.producer = producer;
		this.duration = duration;
		this.imagePath = imagePath;
		this.averageRating = averageRating;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getListOfActors() {
		return listOfActors;
	}

	public void setListOfActors(String listOfActors) {
		this.listOfActors = listOfActors;
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

	public int getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(int averageRating) {
		this.averageRating = averageRating;
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
