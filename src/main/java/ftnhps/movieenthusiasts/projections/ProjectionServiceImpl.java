package ftnhps.movieenthusiasts.projections;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.places.PlaceRepository;
import ftnhps.movieenthusiasts.reservations.Reservation;
import ftnhps.movieenthusiasts.reservations.ReservationRepository;

@Transactional(readOnly = true)
@Service
public class ProjectionServiceImpl implements ProjectionService{

	@Autowired 
	private ProjectionRepository projectionRepository;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public Projection findOne(Long id) {
		return projectionRepository.findOne(id);
	}

	@Override
	public List<Projection> findAll() {
		return projectionRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Projection add(Projection input) {
		input.setPlace( placeRepository.findOne(input.getPlace().getId())  );
		if(input.getPlace() == null)
			return null;
		
		return projectionRepository.save(input);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Projection edit(Long id, Projection input) {
		input.setPlace( placeRepository.findOne(input.getPlace().getId())  );
		if(input.getPlace() == null)
			return null;
		
		if(findOne(id) == null)
			return null;
		input.setId(id);
		return projectionRepository.save(input);
	}

	@Override
	public List<Projection> findProjectionsByPlace(Place place) {
		return projectionRepository.findByPlace(place);
	}

	@Override
	@Transactional(readOnly = false)
	public void recalculateRation(Projection projection) {
		List<Reservation> reservations = reservationRepository.findByDateTime_Projection(projection);
		int sum = projection.getAverageRating();
		int number = 1;
		for(Reservation reservation:reservations) {
			if(reservation.getProjectionRating() > 0) {
				sum += reservation.getProjectionRating();
				number++;
			}		
		}
		projection.setAverageRating((int)sum/number);
		projectionRepository.save(projection);
	}

	@Override
	@Transactional(readOnly = false)
	public Projection delete(Long id) {
		
		Projection projection = projectionRepository.findOne(id);
		if(projection == null)
			return null;
		try {
		projectionRepository.delete(projection);
		}catch (Exception e) {
			System.out.println("Can't deleti projection which is already referenced");
			return null;
		}
		return projection;
	}
	

}
