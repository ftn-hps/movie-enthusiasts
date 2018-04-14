package ftnhps.movieenthusiasts.friendships;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnhps.movieenthusiasts.users.User;
import ftnhps.movieenthusiasts.users.UserService;

@Transactional
@Service
public class FriendshipServiceImpl implements FriendshipService {

	@Autowired
	private FriendshipRepository friendshipRepository;
	@Autowired
	private UserService userService;
	
	@Override
	public Friendship findOne(Long id) {
		return friendshipRepository.findOne(id);
	}

	@Override
	public List<Friendship> findAll() {
		return friendshipRepository.findAll();
	}

	@Override
	public List<Friendship> findAllOfUser(User user) {
		return friendshipRepository.findBySenderOrReceiver(user, user);
	}

	@Override
	public Friendship add(Long senderId, Long receiverId) {
		if(senderId == receiverId)
			return null;
		
		User sender = userService.findOne(senderId);
		User receiver = userService.findOne(receiverId);
		
		if(friendshipRepository.findBySenderAndReceiver(sender, receiver) != null)
			return null;
		if(friendshipRepository.findBySenderAndReceiver(receiver, sender) != null)
			return null;
		
		Friendship friendship = new Friendship(sender, receiver, false);
		friendshipRepository.save(friendship);
		return friendship;
	}

	@Override
	public Friendship accept(Long id, User receiver) {
		Friendship friendship = friendshipRepository.findOne(id);
		if(friendship == null || friendship.getReceiver().getId() != receiver.getId())
			return null;
		
		friendship.setAccepted(true);
		friendshipRepository.save(friendship);
		return friendship;
	}

	@Override
	public boolean remove(Long id, User user) {
		List<Friendship> usersFriendships = findAllOfUser(user);
		Friendship friendship = findOne(id);
		if(!usersFriendships.contains(friendship))
			return false;
		
		friendshipRepository.delete(id);
		return true;
	}

}
