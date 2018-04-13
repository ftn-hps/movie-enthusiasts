package ftnhps.movieenthusiasts.friendships;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ftnhps.movieenthusiasts.users.User;

@Entity
public class Friendship {

	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	@ManyToOne
	@NotNull
	private User sender;

	@ManyToOne
	@NotNull
	private User receiver;

	private boolean isAccepted;

	public Friendship() {}

	public Friendship(User sender, User receiver, boolean isAccepted) {
		this.sender = sender;
		this.receiver = receiver;
		this.isAccepted = isAccepted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

}
