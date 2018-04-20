package ftnhps.movieenthusiasts.fanzone.reservation;

public class PropReservationDTO {
	
	private Long propId;
	
	private Integer quantity;
	
	public PropReservationDTO() {}

	public PropReservationDTO(Long propId, int quantity) {
		this.propId = propId;
		this.quantity = quantity;
	}

	public Long getPropId() {
		return propId;
	}

	public void setPropId(Long propId) {
		this.propId = propId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
