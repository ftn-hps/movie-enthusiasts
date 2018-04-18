package ftnhps.movieenthusiasts.fanzone.propused;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.stereotype.Component;

import ftnhps.movieenthusiasts.users.User;

@Component
public class PropUsedConverter {
	
	public PropUsed fromDTO(PropUsedDTO dto, User forUser) {
		
		if( dto.getName() == null || dto.getName().trim().isEmpty() )
			return null;
		
		if( dto.getDescription() == null || dto.getDescription().trim().isEmpty() )
			return null;
		
		if( dto.getDate() == null || dto.getDate().isBefore(LocalDateTime.now(ZoneId.of("Z"))) )
			return null;
		
		return new PropUsed(forUser, dto.getName(), dto.getDescription(), dto.getDate(), "");
	}
}
