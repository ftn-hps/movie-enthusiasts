package ftnhps.movieenthusiasts.users.scale;

import org.springframework.stereotype.Component;

@Component
public class ScaleConverter {
	
	public Scale fromDTO(ScaleDTO dto) {
		
		if(dto == null || dto.getSilver() == null || dto.getGold() == null)
			return null;
		
		if(dto.getSilver() >= dto.getGold())
			return null;
		
		return new Scale(dto.getSilver(), dto.getGold());
	}

}
