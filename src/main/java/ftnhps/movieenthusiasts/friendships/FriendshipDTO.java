package ftnhps.movieenthusiasts.friendships;

import ftnhps.movieenthusiasts.users.User;

public class FriendshipDTO {

	private Long id;
	private User friend;
	private boolean isPending;
	
	public FriendshipDTO() {}

	public FriendshipDTO(Long id, User friend, boolean isPending) {
		this.id = id;
		this.friend = friend;
		this.isPending = isPending;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	public boolean isPending() {
		return isPending;
	}

	public void setPending(boolean isPending) {
		this.isPending = isPending;
	}

}
