package ftnhps.movieenthusiasts.reservations;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftnhps.movieenthusiasts.DateAndTime.DateAndTime;
import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.projections.Projection;
import ftnhps.movieenthusiasts.users.User;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	List<Reservation> findByUser(User user);
	
	List<Reservation> findByDateTime(DateAndTime dateAndTime);
	
	List<Reservation> findByUserAndDateTime_TimeStampGreaterThan(User user, Long timeStamp);
	
	List<Reservation> findByUserAndDateTime_TimeStampLessThan(User user, Long timeStamp);
	
	List<Reservation> findByDateTime_Projection(Projection projection);
	
	List<Reservation> findByDateTime_Projection_Place(Place place);
}
