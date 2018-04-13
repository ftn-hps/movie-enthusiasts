package ftnhps.movieenthusiasts.friendships;

import java.util.List;

import ftnhps.movieenthusiasts.users.User;

public interface FriendshipService {

	Friendship findOne(Long id);
	
	List<Friendship> findAll();
	
	List<Friendship> findAllOfUser(User user);
	
	Friendship add(Long senderId, Long receiverId);
	
	Friendship accept(Long id, User receiver);
	
	boolean remove(Long id, User user);
}
