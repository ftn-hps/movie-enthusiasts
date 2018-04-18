package ftnhps.movieenthusiasts.fanzone.bid;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftnhps.movieenthusiasts.fanzone.propused.PropUsed;
import ftnhps.movieenthusiasts.fanzone.propused.PropUsedService;
import ftnhps.movieenthusiasts.users.User;

@Component
public class BidConverter {
	
	@Autowired
	PropUsedService propUsedService;
	
	public Bid fromDTO(BidDTO dto, User forUser) {
		if(dto.getPropId() == null)
			return null;
		
		PropUsed propUsed = propUsedService.findOne(dto.getPropId());
		if(propUsed == null || propUsed.getApproved() == null || !propUsed.getApproved())
			return null;
		
		if(propUsed.getDate().isBefore(LocalDateTime.now(ZoneId.of("Z"))))
			return null;
		
		if(dto.getBid() == null)
			return null;
		
		return new Bid(propUsed, forUser, dto.getBid());
	}

}
