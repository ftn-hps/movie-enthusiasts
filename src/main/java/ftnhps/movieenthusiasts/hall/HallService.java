package ftnhps.movieenthusiasts.hall;

import java.util.List;

import ftnhps.movieenthusiasts.places.Place;

public interface HallService {

	Hall findOne(Long id);
	
	List<Hall> findAll();
	
	List<Hall> findByPalce(Place place);
	
	Hall add(Hall input);
	
	Hall edit(Long id, Hall input);

	Hall delete(Long id);
}
