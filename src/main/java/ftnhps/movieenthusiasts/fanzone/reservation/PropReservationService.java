package ftnhps.movieenthusiasts.fanzone.reservation;

import java.util.List;

public interface PropReservationService {
	
	PropReservation findOne(Long id);
	
	List<PropReservation> findAll(Long userId, Boolean canceled);
	
	PropReservation add(PropReservation input);
	
	PropReservation edit(Long id, PropReservation input);
}
