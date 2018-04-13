package ftnhps.movieenthusiasts.friendships;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ftnhps.movieenthusiasts.users.User;

@Component
public class FriendshipConverter {
	
	public FriendshipDTO toDTO(Friendship input, User forUser) {
		FriendshipDTO dto;
		if(forUser.getId() == input.getSender().getId())
			dto = new FriendshipDTO(input.getId(), input.getReceiver(), false);
		else
			dto = new FriendshipDTO(input.getId(), input.getSender(), !input.isAccepted());
		
		return dto;
	}
	
	public List<FriendshipDTO> toDTO(List<Friendship> input, User forUser) {
		List<FriendshipDTO> dto = new ArrayList<>();
		for(Friendship friendship : input)
		{
			dto.add(toDTO(friendship, forUser));
		}
		return dto;
	}

}
