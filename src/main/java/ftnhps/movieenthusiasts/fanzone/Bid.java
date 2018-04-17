package ftnhps.movieenthusiasts.fanzone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Bid {
	
	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@NotNull
	private Long propId;
	
	@NotNull
	private Long bidderId;
	
	@NotBlank
	private String bidderName;

	@NotNull
	@Min(0)
	private Double bid;
	
	public Bid() {}

	public Bid(Long propId, Long bidderId, Double bid) {
		this.propId = propId;
		this.bidderId = bidderId;
		this.bid = bid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPropId() {
		return propId;
	}

	public void setPropId(Long propId) {
		this.propId = propId;
	}

	public Long getBidderId() {
		return bidderId;
	}

	public void setBidderId(Long bidderId) {
		this.bidderId = bidderId;
	}
	
	public String getBidderName() {
		return bidderName;
	}

	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}
	
}
