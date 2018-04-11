package ftnhps.movieenthusiasts.projections;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import ftnhps.movieenthusiasts.places.Place;


@Entity
public class Projection {
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	@ManyToOne(optional = false)
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
	
	@Max(5)
	@Min(1)
	private int averateRating;
	
	@NotBlank
	private String shortDescription;
	
	//TODO dodati jos salu i termine i odluciti da li ce se oni cuvati
	//samo kao plaint string ili ce biti posebne tabele
	//za sad su json string
	
	@NotBlank
	private String projectionHalls;
	
	@NotBlank
	private String projectionTimes;
	
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
			String projectionHalls,
			String projectionTimes,
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
		this.projectionHalls = projectionHalls;
		this.projectionTimes = projectionTimes;
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

	public String getProjectionHalls() {
		return projectionHalls;
	}

	public void setProjectionHalls(String projectionHalls) {
		this.projectionHalls = projectionHalls;
	}

	public String getProjectionTimes() {
		return projectionTimes;
	}

	public void setProjectionTimes(String projectionTimes) {
		this.projectionTimes = projectionTimes;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
