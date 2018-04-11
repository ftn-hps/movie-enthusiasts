package ftnhps.movieenthusiasts.reservations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Reservation {
	
	@Id
	@GeneratedValue
	private Long id;
	
}
