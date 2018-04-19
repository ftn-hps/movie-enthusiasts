package ftnhps.movieenthusiasts.reservations;

import java.util.List;

import ftnhps.movieenthusiasts.users.User;

public class ReservationDTO {

	private long dateAndTimeId;
	
	private List<Integer> seats;
	
	private List<User> friends;
	
	private int discount;
	
	
	public ReservationDTO() {}

	public ReservationDTO(long dateAndTimeId, List<Integer> seats, List<User> friends) {
		this.dateAndTimeId = dateAndTimeId;
		this.seats = seats;
		this.friends = friends;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public long getDateAndTimeId() {
		return dateAndTimeId;
	}

	public void setDateAndTimeId(long dateAndTimeId) {
		this.dateAndTimeId = dateAndTimeId;
	}

	public List<Integer> getSeats() {
		return seats;
	}

	public void setSeats(List<Integer> seats) {
		this.seats = seats;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

}
