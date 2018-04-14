package ftnhps.movieenthusiasts.friendships;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftnhps.movieenthusiasts.users.User;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

	Friendship findBySenderAndReceiver(User sender, User receiver);

	List<Friendship> findBySenderOrReceiver(User sender, User receiver);

}
