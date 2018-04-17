package ftnhps.movieenthusiasts.projections;

import java.util.List;

import ftnhps.movieenthusiasts.places.Place;

public interface ProjectionService {

	Projection findOne (Long id);
	
	List<Projection> findAll();
	
	List<Projection> findProjectionsByPlace(Place place);
	
	Projection add(Projection input);
	
	Projection edit(Long id, Projection input);

	void recalculateRation(Projection projection);
	
}
