package ftnhps.movieenthusiasts.reservations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftnhps.movieenthusiasts.DateAndTime.DateAndTime;
import ftnhps.movieenthusiasts.DateAndTime.DateAndTimeService;
import ftnhps.movieenthusiasts.users.User;

@Component
public class ReservationConverter {
	
	@Autowired
	private DateAndTimeService dateAndTimeService;

	public List<Reservation> fromDTO(ReservationDTO dto, User forUser) {
		List<Reservation> ret = new ArrayList<>();
		
		if(dto.getSeats() == null || dto.getSeats().size() < 1)
			return null;
		
		if(dto.getFriends() != null
			&& dto.getFriends().size() != dto.getSeats().size() + 1)
				return null;
		
		// Reserve available seats in DateAndTime
		DateAndTime dateAndTime = dateAndTimeService.findOne(dto.getDateAndTimeId());
		String layout = dateAndTime.getReservationLayout();
		
		for(Integer seat : dto.getSeats()) {
			if(layout.charAt(seat) != 'o')
				return null;
			
			layout = layout.substring(0, seat)
					+ 'r'
					+ layout.substring(seat + 1);
		}
		dateAndTime.setReservationLayout(layout);
		dateAndTimeService.edit(dto.getDateAndTimeId(), dateAndTime);
		
		// Reserve for self
		ret.add(new Reservation(0,
				dateAndTime,
				dto.getSeats().get(0),
				forUser));
		if(dto.getFriends() == null)
			return ret;
		// Reserve for friends
		for(int i = 0; i < dto.getFriends().size(); i++)
		{
			ret.add(new Reservation(0,
					dateAndTime,
					dto.getSeats().get(i+1),
					dto.getFriends().get(i)));
		}
		return ret;
	}
	
	public List<Reservation> fromDTOFast(ReservationDTO dto, User forUser) {
		List<Reservation> ret = new ArrayList<>();
		
		if(dto.getSeats() == null || dto.getSeats().size() < 1)
			return null;
		
		
		// Reserve available seats in DateAndTime
		DateAndTime dateAndTime = dateAndTimeService.findOne(dto.getDateAndTimeId());
		String layout = dateAndTime.getReservationLayout();
		
		for(Integer seat : dto.getSeats()) {
			if(layout.charAt(seat) != 'o')
				return null;
			
			layout = layout.substring(0, seat)
					+ 'r'
					+ layout.substring(seat + 1);
		}
		dateAndTime.setReservationLayout(layout);
		dateAndTimeService.edit(dto.getDateAndTimeId(), dateAndTime);
		
	
		//get all seats
		for(int i = 0; i < dto.getSeats().size(); i++)
		{
			ret.add(new Reservation(dto.getDiscount(),
					dateAndTime,
					dto.getSeats().get(i),
					null));
		}
		return ret;
	}

}
