package ftnhps.movieenthusiasts.reservations;

import java.util.List;

import ftnhps.movieenthusiasts.DateAndTime.DateAndTime;
import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.projections.Projection;
import ftnhps.movieenthusiasts.users.User;

public interface ReservationService {

	Reservation findOne (Long id);
	
	List<Reservation> findAll();
	
	List<Reservation> findByDateTime(DateAndTime dateAndTime);
	
	List<Reservation> findByUser(User user);
	
	List<Reservation> findByDateTimeProjection(Projection projection);
	
	List<Reservation> findByDateTimeProjectionPlace(Place place);
	
	Reservation add(Reservation reservation);
	
	List<Reservation> add(List<Reservation> reservations);
	
	boolean remove(Long id, User user);

	Reservation edit(Long id, Reservation reservation);
	
}
