package ftnhps.movieenthusiasts.places;

import java.util.List;

public interface PlaceService {

	Place findOne(Long id);
	
	List<Place> findAll();
	
	List<Place> findAll(PlaceType type);
	
	Place add(Place input);
	
	Place edit(Long id, Place input);
	
}
