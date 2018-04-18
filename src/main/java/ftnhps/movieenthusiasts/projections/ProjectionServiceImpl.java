package ftnhps.movieenthusiasts.projections;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.places.PlaceRepository;
import ftnhps.movieenthusiasts.reservations.Reservation;
import ftnhps.movieenthusiasts.reservations.ReservationRepository;

@Transactional
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
	public Projection add(Projection input) {
		input.setPlace( placeRepository.findOne(input.getPlace().getId())  );
		if(input.getPlace() == null)
			return null;
		
		return projectionRepository.save(input);
	}

	@Override
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
		
	}

	@Override
	public Projection delete(Long id) {
		
		Projection projection = projectionRepository.findOne(id);
		if(projection == null)
			return null;
		try {
		projectionRepository.delete(projection);
		}catch (Exception e) {
			System.out.println("Can't deleti projection which is already referenced");
		}
		return projection;
	}
	

}
