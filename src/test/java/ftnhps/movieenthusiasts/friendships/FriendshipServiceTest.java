package ftnhps.movieenthusiasts.friendships;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ftnhps.movieenthusiasts.users.User;
import ftnhps.movieenthusiasts.users.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendshipServiceTest {

	@Autowired
	FriendshipService friendshipService;
	@Autowired
	UserService userService;

	@Test
	public void testFindOne() {
		Friendship dbFriendship = friendshipService.findOne(FriendshipConstants.DB_ID);
		assertThat(dbFriendship).isNotNull();

		assertThat(dbFriendship.getId()).isEqualTo(FriendshipConstants.DB_ID);
		assertThat(dbFriendship.getSender().getId()).isEqualTo(FriendshipConstants.DB_SENDER_ID);
		assertThat(dbFriendship.getReceiver().getId()).isEqualTo(FriendshipConstants.DB_RECEIVER_ID);
		assertThat(dbFriendship.isAccepted()).isEqualTo(FriendshipConstants.DB_ACCEPTED);
	}

	@Test
	public void testFindAll() {
		List<Friendship> friendships = friendshipService.findAll();
		assertThat(friendships).hasSize(FriendshipConstants.DB_COUNT);
	}

	@Test
	@Transactional
	public void testAdd() {
		int dbSizeBeforeAdd = friendshipService.findAll().size();
		Friendship dbFriendship = friendshipService.add(
				FriendshipConstants.NEW_SENDER_ID, 
				FriendshipConstants.NEW_RECEIVER_ID);
		assertThat(dbFriendship).isNotNull();

		List<Friendship> friendships = friendshipService.findAll();
		assertThat(friendships).hasSize(dbSizeBeforeAdd + 1);
		dbFriendship = friendships.get(friendships.size() - 1);
		assertThat(dbFriendship.getSender().getId()).isEqualTo(FriendshipConstants.NEW_SENDER_ID);
		assertThat(dbFriendship.getReceiver().getId()).isEqualTo(FriendshipConstants.NEW_RECEIVER_ID);
		assertThat(dbFriendship.isAccepted()).isEqualTo(FriendshipConstants.NEW_ACCEPTED);
	}
	
	@Test
	@Transactional
	public void testAccept() {
		User receiver = userService.findOne(FriendshipConstants.DB_RECEIVER_ID);
		Friendship dbFriendship = friendshipService.accept(FriendshipConstants.DB_ID, receiver);
		
		assertThat(dbFriendship == null);
	}

	@Test
	@Transactional
	public void testRemove() {
		int dbSizeBeforeRemove = friendshipService.findAll().size();
		User receiver = userService.findOne(FriendshipConstants.DB_RECEIVER_ID);
		
		friendshipService.remove(FriendshipConstants.DB_ID, receiver);
		
		List<Friendship> friendships = friendshipService.findAll();
		assertThat(friendships).hasSize(dbSizeBeforeRemove - 1);
		
		Friendship dbFriendship = friendshipService.findOne(FriendshipConstants.DB_ID);
		assertThat(dbFriendship).isNull();
	}

}