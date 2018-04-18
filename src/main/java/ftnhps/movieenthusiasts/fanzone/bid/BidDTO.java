package ftnhps.movieenthusiasts.fanzone.bid;

public class BidDTO {
	
	private Long propId;
	
	private Double bid;
	
	public BidDTO() {}

	public BidDTO(Long propId, Double bid) {
		this.propId = propId;
		this.bid = bid;
	}

	public Long getPropId() {
		return propId;
	}

	public void setPropId(Long propId) {
		this.propId = propId;
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

}
