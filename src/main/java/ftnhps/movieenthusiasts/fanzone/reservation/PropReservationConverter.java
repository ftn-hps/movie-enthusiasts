package ftnhps.movieenthusiasts.fanzone.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftnhps.movieenthusiasts.fanzone.propnew.PropNew;
import ftnhps.movieenthusiasts.fanzone.propnew.PropNewService;
import ftnhps.movieenthusiasts.users.User;
import ftnhps.movieenthusiasts.users.UserService;

@Component
public class PropReservationConverter {
	
	@Autowired
	UserService userService;
	@Autowired
	PropNewService propNewService;
	
	public PropReservation fromDTO(PropReservationDTO dto, User user) {
		
		if(dto.getPropId() == null)
			return null;
		
		PropNew prop = propNewService.findOne(dto.getPropId());
		if(prop == null)
			return null;
		
		if(dto.getQuantity() == null || dto.getQuantity() < 1)
			return null;
		
		return new PropReservation(prop, user, dto.getQuantity());
	}
}
