package ftnhps.movieenthusiasts.fanzone.propnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.places.PlaceService;

@Component
public class PropNewConverter {
	
	@Autowired
	private PlaceService placeService;
	
	public PropNew fromDTO(PropNewDTO dto) {
		
		if(dto.getPlaceId() == null)
			return null;
		
		Place place = placeService.findOne(dto.getPlaceId());
		if(place == null)
			return null;
		
		if(dto.getName() == null || dto.getName().trim().isEmpty())
			return null;
		
		if(dto.getDescription() == null || dto.getDescription().trim().isEmpty())
			return null;
		
		return new PropNew(place, dto.getName(), dto.getDescription(), "");
	}
}
