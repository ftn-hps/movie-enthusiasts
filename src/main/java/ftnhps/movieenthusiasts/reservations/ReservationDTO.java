package ftnhps.movieenthusiasts.reservations;

import java.util.List;

public class ReservationDTO {

	private long dateAndTimeId;
	
	private List<Integer> seats;
	
	private List<Long> friendIds;
	
	private int discount;
	
	
	public ReservationDTO() {}

	public ReservationDTO(long dateAndTimeId, List<Integer> seats, List<Long> friendIds) {
		this.dateAndTimeId = dateAndTimeId;
		this.seats = seats;
		this.friendIds = friendIds;
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

	public List<Long> getFriendIds() {
		return friendIds;
	}

	public void setFriendIds(List<Long> friendIds) {
		this.friendIds = friendIds;
	}

}
