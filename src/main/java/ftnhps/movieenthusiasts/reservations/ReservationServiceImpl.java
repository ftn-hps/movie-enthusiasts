package ftnhps.movieenthusiasts.reservations;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnhps.movieenthusiasts.DateAndTime.DateAndTime;
import ftnhps.movieenthusiasts.DateAndTime.DateAndTimeService;
import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.places.PlaceService;
import ftnhps.movieenthusiasts.projections.Projection;
import ftnhps.movieenthusiasts.projections.ProjectionService;
import ftnhps.movieenthusiasts.users.User;

@Transactional
@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private DateAndTimeService dateAndTimeService;
	@Autowired
	private ProjectionService projectionService;
	@Autowired 
	private PlaceService placeService;
	
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
	public List<Reservation> add(List<Reservation> reservations) {
		List<Reservation> ret = new ArrayList<>();
		for(Reservation reservation : reservations)
			ret.add(reservationRepository.save(reservation));

		return ret;
	}
	
	@Override
	public boolean remove(Long id, User user) {
		List<Reservation> userReservations = findByUser(user);
		Reservation reservation = findOne(id);
		if(!userReservations.contains(reservation))
			return false;
		
		DateAndTime dateAndTime = reservation.getDateTime();
		String layout = dateAndTime.getReservationLayout();
		
		layout = layout.substring(0, reservation.getSeat())
				+ 'o'
				+ layout.substring(reservation.getSeat() + 1);
		
		dateAndTime.setReservationLayout(layout);
		dateAndTimeService.edit(dateAndTime.getId(), dateAndTime);
		
		reservationRepository.delete(id);
		return true;
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

	@Override
	public Reservation rate(Reservation reservation, RateDTO input, User user) {
		List<Reservation> userReservations = findByUser(user);
		if(!userReservations.contains(reservation))
			return null;
		reservation.setAmbientRating(input.getRateAmbient());
		reservation.setProjectionRating(input.getRateProjection());
		
		Reservation ret = reservationRepository.save(reservation); 
		placeService.recalculateRating( ret.getDateTime().getProjection().getPlace());
		projectionService.recalculateRation(ret.getDateTime().getProjection());
		return ret;
	}

	@Override
	public List<Reservation> findHistory(User user) {
		List<Reservation> reservations = reservationRepository.findByUser(user);
		Long timestamp = System.currentTimeMillis()/1000;
		List<Reservation> ret = new ArrayList<Reservation>();
		for(Reservation reservation : reservations) {
			if(timestamp >reservation.getDateTime().getTimeStamp())
				ret.add(reservation);
		}
		return ret;
	}


}
