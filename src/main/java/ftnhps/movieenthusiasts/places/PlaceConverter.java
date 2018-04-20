package ftnhps.movieenthusiasts.places;

import org.springframework.stereotype.Component;

@Component
public class PlaceConverter {
	
	public Place fromDTO(PlaceDTO dto) {
		
		if(dto.getName() == null || dto.getName().trim().isEmpty())
			return null;
		
		if(dto.getAddress() == null || dto.getAddress().trim().isEmpty())
			return null;
		
		if(dto.getDescription() == null || dto.getAddress().trim().isEmpty())
			return null;
		
		if(dto.getType() == null)
			return null;
		
		try {
			PlaceType.valueOf(dto.getType().toUpperCase());
		}catch (IllegalArgumentException e) {
			return null;
		}
		
		if(dto.getLat() != null && (dto.getLat() > 90 || dto.getLat() < -90))
			return null;
		
		if(dto.getLng() != null && (dto.getLng() > 180 || dto.getLng() < -180))
			return null;
		
		return new Place(PlaceType.valueOf(dto.getType().toUpperCase()), dto.getName(), 1, dto.getAddress(), dto.getDescription(), dto.getLat(), dto.getLng());
	}

}
