package ftnhps.movieenthusiasts.reservations;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnhps.movieenthusiasts.DateAndTime.DateAndTime;
import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.projections.Projection;
import ftnhps.movieenthusiasts.users.User;

@Transactional
@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationRepository reservationRepository;
	
	
	@Override
	public Reservation findOne(Long id) {
		return reservationRepository.findOne(id);
	}

	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Override
	public List<Reservation> findByDateTime(DateAndTime dateAndTime) {
		return reservationRepository.findByDateTime(dateAndTime);
	}

	@Override
	public List<Reservation> findByUser(User user) {
		return reservationRepository.findByUser(user);
	}

	@Override
	public List<Reservation> findByDateTimeProjection(Projection projection) {
		return reservationRepository.findByDateTime_Projection(projection);
	}

	@Override
	public Reservation add(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public Reservation edit(Long id, Reservation reservation) {
		if(findOne(id) == null)
			return null;
		reservation.setId(id);
		return reservationRepository.save(reservation);
	}

	@Override
	public List<Reservation> findByDateTimeProjectionPlace(Place place) {
		return reservationRepository.findByDateTime_Projection_Place(place);
	}


}
